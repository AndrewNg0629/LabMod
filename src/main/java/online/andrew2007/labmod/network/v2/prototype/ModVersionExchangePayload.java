package online.andrew2007.labmod.network.v2.prototype;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.login.LoginQueryRequestPayload;
import net.minecraft.util.Identifier;

public record ModVersionExchangePayload(String modVersion) implements LoginQueryRequestPayload {
    public static final Identifier ID = Identifier.of("labmod", "mod_version_exchange");

    @Override
    public Identifier id() {
        return ID;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeString(modVersion);
    }
}
