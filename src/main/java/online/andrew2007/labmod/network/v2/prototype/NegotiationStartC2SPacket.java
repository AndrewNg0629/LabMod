package online.andrew2007.labmod.network.v2.prototype;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.listener.ClientLoginPacketListener;
import net.minecraft.network.listener.ServerLoginPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.PacketType;

public class NegotiationStartC2SPacket implements Packet<ServerLoginPacketListener> {
    public static PacketCodec<PacketByteBuf, NegotiationStartC2SPacket> CODEC = Packet.createCodec(NegotiationStartC2SPacket::write, NegotiationStartC2SPacket::new);
    public final String clientModVersion;

    public NegotiationStartC2SPacket(String clientModVersion) {
        this.clientModVersion = clientModVersion;
    }

    private NegotiationStartC2SPacket(PacketByteBuf buf) {
        this.clientModVersion = buf.readString();
    }

    @Override
    public PacketType<? extends Packet<ServerLoginPacketListener>> getPacketId() {
        return NetworkPrototype.NEGOTIATION_START_C2S;
    }

    private void write(PacketByteBuf buf) {
        buf.writeString(this.clientModVersion);
    }

    @Override
    public void apply(ServerLoginPacketListener listener) {

    }
}
