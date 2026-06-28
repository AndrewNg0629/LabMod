package top.aenp.labmod.network.v2.prototype;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public record TestLoginS2CPayload(String hello) implements MythicLoginS2CPayload {
    public static final Identifier ID = Identifier.of("labmod", "test_s2c");

    public TestLoginS2CPayload(PacketByteBuf buf) {
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
    public void handle(ClientLoginNetworkHandlerMethodInjections handler) {
        handler.labmod$onTestLoginS2C(this);
    }
}
