package top.aenp.labmod.network.v2.prototype;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.login.LoginQueryRequestPayload;
import net.minecraft.util.Identifier;

public interface MythicLoginS2CPayload extends LoginQueryRequestPayload {
    @Override
    default Identifier id() {
        return Identifier.of("mw", "login_s2c_common"); //No use.
    }

    @Override
    default void write(PacketByteBuf buf) {
        buf.writeIdentifier(this.mythicId());
        this.mythicWrite(buf);
    }

    Identifier mythicId();

    void mythicWrite(PacketByteBuf buf);

    void handle(ClientLoginNetworkHandlerMethodInjections handler);
}
