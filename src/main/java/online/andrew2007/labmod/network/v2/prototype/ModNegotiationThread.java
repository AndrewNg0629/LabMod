package online.andrew2007.labmod.network.v2.prototype;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.listener.ServerLoginPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import net.minecraft.text.Text;
import online.andrew2007.labmod.LabMod;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ModNegotiationThread extends Thread {
    private static final AtomicInteger THREAD_NUMBER = new AtomicInteger(1);

    private final ServerLoginNetworkHandler handler;
    private final ClientConnection connection;
    private final GameProfile profile;
    private final PlayerManager manager;
    public final ConcurrentLinkedQueue<Packet<ServerLoginPacketListener>> C2SQueue = new ConcurrentLinkedQueue<>();
    public final Object lock = new Object();

    public ModNegotiationThread(ServerLoginNetworkHandler handler, ClientConnection connection, GameProfile profile, PlayerManager manager) {
        super("LabMod Negotiator #" + THREAD_NUMBER.incrementAndGet());
        this.handler = handler;
        this.connection = connection;
        this.profile = profile;
        this.manager = manager;
    }

    @Override
    public void run() {
        try {
            this.connection.send(new NegotiationStartS2CPacket(LabMod.MOD_VERSION));

            this.handler.labmod$finishModNegotiation(this.profile, this.manager);
        } catch (Exception e) {
            if (this.handler.isConnectionOpen()) {
                handler.disconnect(Text.of("Exception negotiating:" + e.getMessage()));
            }
            LabMod.LOGGER.error("Mod negotiation aborted.", e);
        }
    }

    private void checkInterrupted() throws InterruptedException {
        if (this.isInterrupted()) {
            throw new InterruptedException();
        }
    }

    private <P extends Packet<ServerLoginPacketListener>> P pollPacketAndCast(Class<P> packetType) throws InterruptedException {
        if (this.C2SQueue.isEmpty()) {
            synchronized (this.lock) {
                this.lock.wait(3000);
            }
        }
        Packet<ServerLoginPacketListener> packet = this.C2SQueue.poll();
        if (packet != null) {
            if (!packetType.isAssignableFrom(packet.getClass())) {
                throw new RuntimeException("Unexpected packet: " + packet);
            } else {
                return packetType.cast(packet);
            }
        } else {
            throw new RuntimeException("Packet expectation timed out: " + packetType);
        }
    }
}
