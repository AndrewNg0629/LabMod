package top.aenp.labmod.network.v2.prototype;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.login.LoginQueryResponsePayload;
import net.minecraft.util.Identifier;

public interface MythicLoginC2SPayload extends LoginQueryResponsePayload {
    @Override
    default void write(PacketByteBuf buf) {
        buf.writeIdentifier(this.mythicId());
        this.mythicWrite(buf);
    }

    Identifier mythicId();

    void mythicWrite(PacketByteBuf buf);

    void handle(ServerLoginNetworkHandlerMethodInjections handler);
}
