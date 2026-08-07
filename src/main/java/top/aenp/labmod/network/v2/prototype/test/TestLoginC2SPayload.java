package top.aenp.labmod.network.v2.prototype.test;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.Identifier;
import top.aenp.labmod.network.v2.prototype.injections.ServerLoginNetworkHandlerMethodInjections;
import top.aenp.labmod.network.v2.prototype.payloads.interfaces.MythicLoginC2SPayload;

public record TestLoginC2SPayload(String hello) implements MythicLoginC2SPayload {
    public static final Identifier ID = Identifier.of("labmod", "test_c2s");
    public static final PacketCodec<PacketByteBuf, TestLoginC2SPayload> CODEC = new PacketCodec<>() {
        @Override
        public TestLoginC2SPayload decode(PacketByteBuf buf) {
            return new TestLoginC2SPayload(buf.readString());
        }
        @Override
        public void encode(PacketByteBuf buf, TestLoginC2SPayload value) {
            buf.writeString(value.hello());
        }
    };
    public TestLoginC2SPayload(PacketByteBuf buf) {
        this(buf.readString());
    }

    @Override
    public Identifier mythicId() {
        return ID;
    }

    @Override
    public void handle(ServerLoginNetworkHandlerMethodInjections handler) {
    }
}
