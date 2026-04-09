package online.andrew2007.labmod.network.v2.prototype;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.listener.ClientLoginPacketListener;
import net.minecraft.network.listener.ServerLoginPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.login.LoginQueryRequestS2CPacket;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import net.minecraft.text.Text;
import online.andrew2007.labmod.LabMod;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ModNegotiationTask implements Runnable {
    private final ServerLoginNetworkHandler handler;
    private final ClientConnection connection;
    private final GameProfile profile;
    private final PlayerManager manager;
    public final ConcurrentLinkedQueue<Packet<ServerLoginPacketListener>> C2SQueue = new ConcurrentLinkedQueue<>();
    public final Object lock = new Object();
    public final AtomicBoolean disconnected = new AtomicBoolean(false);
    public static final AtomicInteger threadNumber = new AtomicInteger(0);

    public ModNegotiationTask(ServerLoginNetworkHandler handler, ClientConnection connection, GameProfile profile, PlayerManager manager) {
        this.handler = handler;
        this.connection = connection;
        this.profile = profile;
        this.manager = manager;
    }

    @Override
    public void run() {
        try {
            Thread.currentThread().setName("Negotiation Thread #" + threadNumber.incrementAndGet());
            this.sendSafely(new LoginQueryRequestS2CPacket(Integer.MIN_VALUE, new ModVersionExchangePayload(LabMod.MOD_VERSION)));
            Thread.sleep(3000);
            this.throwIfShouldExit();
            this.handler.labmod$finishModNegotiation(this.profile, this.manager);
        } catch (Exception e) {
            if (this.disconnected.compareAndSet(false, true)) {
                handler.disconnect(Text.of("Exception negotiating:" + e));
            }
            LabMod.LOGGER.error("Mod negotiation aborted: {}", e.toString());
        }
    }

    private void throwIfShouldExit() throws InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException();
        }
    }

    private void sendSafely(Packet<ClientLoginPacketListener> packet) throws InterruptedException {
        this.throwIfShouldExit();
        this.connection.send(packet);
    }

    private <P extends Packet<ServerLoginPacketListener>> P pollPacketAndCast(Class<P> packetType) throws InterruptedException {
        P result = null;
        this.throwIfShouldExit();
        if (this.C2SQueue.isEmpty()) {
            synchronized (this.lock) {
                this.lock.wait(3000);
            }
        }
        Packet<ServerLoginPacketListener> packet = this.C2SQueue.poll();
        if (packet != null) {
            if (!packetType.isAssignableFrom(packet.getClass())) {
                this.disconnectAndInterrupt(Text.of("Received unexpected packet: " + packet));
            } else {
                result = packetType.cast(packet);
            }
        } else {
            this.disconnectAndInterrupt(Text.of("Negotiation timed out. Please check your connection and have LabMod installed and well configured."));
        }
        this.throwIfShouldExit();
        return result;
    }

    private void disconnectAndInterrupt(Text reason) throws InterruptedException {
        if (this.disconnected.compareAndSet(false, true)) {
            this.handler.disconnect(reason);
            Thread.currentThread().interrupt();
        }
        this.throwIfShouldExit();
    }
}
