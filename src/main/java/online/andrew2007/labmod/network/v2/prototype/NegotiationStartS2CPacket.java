package online.andrew2007.labmod.network.v2.prototype;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.listener.ClientLoginPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.PacketType;
import online.andrew2007.labmod.LabMod;

public class NegotiationStartS2CPacket implements Packet<ClientLoginPacketListener> {
    public static PacketCodec<PacketByteBuf, NegotiationStartS2CPacket> CODEC = Packet.createCodec(NegotiationStartS2CPacket::write, NegotiationStartS2CPacket::new);
    public final String serverModVersion;

    public NegotiationStartS2CPacket(String serverModVersion) {
        this.serverModVersion = serverModVersion;
    }

    private NegotiationStartS2CPacket(PacketByteBuf buf) {
        this.serverModVersion = buf.readString();
    }

    @Override
    public PacketType<? extends Packet<ClientLoginPacketListener>> getPacketId() {
        return NetworkPrototype.NEGOTIATION_START_S2C;
    }

    private void write(PacketByteBuf buf) {
        buf.writeString(this.serverModVersion);
    }

    @Override
    public void apply(ClientLoginPacketListener listener) {
        ((ClientLoginNetworkHandlerInjection) listener).labmod$onNegotiationStartS2CPacket(this);
    }
}
