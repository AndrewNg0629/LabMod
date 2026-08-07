package top.aenp.labmod.network.v2.prototype.payloads;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import top.aenp.labmod.network.v2.prototype.injections.ServerPlayNetworkHandlerMethodInjections;
import top.aenp.labmod.network.v2.prototype.payloads.interfaces.MythicPlayC2SPayload;

public class BedIdleSignalPayload implements MythicPlayC2SPayload {
    public static final Id<BedIdleSignalPayload> ID = new Id<>(Identifier.of("labmod", "bed_idle_signal"));
    public static final PacketCodec<PacketByteBuf, BedIdleSignalPayload> CODEC = new PacketCodec<>() {
        @Override
        public BedIdleSignalPayload decode(PacketByteBuf buf) {
            return new BedIdleSignalPayload();
        }

        @Override
        public void encode(PacketByteBuf buf, BedIdleSignalPayload value) {
        }
    };
    @Override
    public void handle(ServerPlayNetworkHandlerMethodInjections handler) {
        //TODO Handle
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
