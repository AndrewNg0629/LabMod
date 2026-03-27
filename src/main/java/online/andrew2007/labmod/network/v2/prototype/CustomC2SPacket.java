package online.andrew2007.labmod.network.v2.prototype;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.listener.ServerLoginPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.PacketType;

public class CustomC2SPacket implements Packet<ServerLoginPacketListener> {
    public static final PacketCodec<PacketByteBuf, CustomC2SPacket> CODEC = new PacketCodec<>() {
        @Override
        public CustomC2SPacket decode(PacketByteBuf buf) {
            return new CustomC2SPacket(buf.readString(1024));
        }

        @Override
        public void encode(PacketByteBuf buf, CustomC2SPacket value) {
            buf.writeString(value.data, 1024);
        }
    };

    public String data;

    public CustomC2SPacket(String data) {
        this.data = data;
    }

    @Override
    public PacketType<? extends Packet<ServerLoginPacketListener>> getPacketId() {
        return NetworkPrototype.CUSTOM_C2S;
    }

    @Override
    public void apply(ServerLoginPacketListener listener) {
        ((ServerLoginNetworkHandlerInjection) listener).labmod$onCustomC2SPacket(this);
    }
}
