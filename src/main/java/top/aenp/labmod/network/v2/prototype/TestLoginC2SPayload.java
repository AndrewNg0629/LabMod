package top.aenp.labmod.network.v2.prototype;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public record TestLoginC2SPayload(String hello) implements MythicLoginC2SPayload {
    public static final Identifier ID = Identifier.of("labmod", "test_c2s");
    public TestLoginC2SPayload(PacketByteBuf buf) {
        this(buf.readString());
    }
    @Override
    public Identifier mythicId() {
        return ID;
    }

    @Override
    public void mythicWrite(PacketByteBuf buf) {
        buf.writeString(hello);
    }

    @Override
    public void handle(ServerLoginNetworkHandlerMethodInjections handler) {
        handler.labmod$onTestLoginC2S(this);
    }
}
