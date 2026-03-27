package online.andrew2007.labmod.network.v2.prototype;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.listener.ClientLoginPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.PacketType;

public class CustomS2CPacket implements Packet<ClientLoginPacketListener> {
    public static PacketCodec<PacketByteBuf, CustomS2CPacket> CODEC = new PacketCodec<PacketByteBuf, CustomS2CPacket>() {
        @Override
        public CustomS2CPacket decode(PacketByteBuf buf) {
            return new CustomS2CPacket(buf.readString(1024));
        }

        @Override
        public void encode(PacketByteBuf buf, CustomS2CPacket value) {
            buf.writeString(value.data, 1024);
        }
    };

    public String data;

    public CustomS2CPacket(String data) {
        this.data = data;
    }

    @Override
    public PacketType<? extends Packet<ClientLoginPacketListener>> getPacketId() {
        return NetworkPrototype.CUSTOM_S2C;
    }

    @Override
    public void apply(ClientLoginPacketListener listener) {
        ((ClientLoginNetworkHandlerInjection) listener).labmod$onCustomS2CPacket(this);
    }
}
