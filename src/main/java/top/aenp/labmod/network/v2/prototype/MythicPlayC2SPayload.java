package top.aenp.labmod.network.v2.prototype;

import net.minecraft.network.packet.CustomPayload;

public interface MythicPlayC2SPayload extends CustomPayload {
    void handle(ServerPlayNetworkHandlerMethodInjections handler);
}
