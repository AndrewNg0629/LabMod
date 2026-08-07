package top.aenp.labmod.network.v2.prototype.payloads;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.Identifier;
import top.aenp.labmod.network.v2.prototype.injections.ServerLoginNetworkHandlerMethodInjections;
import top.aenp.labmod.network.v2.prototype.payloads.interfaces.MythicLoginC2SPayload;

public record LoginModVersionC2SPayload(String modVersion) implements MythicLoginC2SPayload {
    public static final Identifier ID = Identifier.of("labmod", "mod_version_c2s");
    public static final PacketCodec<PacketByteBuf, LoginModVersionC2SPayload> CODEC = new PacketCodec<>() {
        @Override
        public LoginModVersionC2SPayload decode(PacketByteBuf buf) {
            return new LoginModVersionC2SPayload(buf.readString());
        }
        @Override
        public void encode(PacketByteBuf buf, LoginModVersionC2SPayload value) {
            buf.writeString(value.modVersion);
        }
    };

    @Override
    public Identifier mythicId() {
        return ID;
    }

    @Override
    public void handle(ServerLoginNetworkHandlerMethodInjections handler) {
        handler.labmod$onModVersion(this);
    }
}
