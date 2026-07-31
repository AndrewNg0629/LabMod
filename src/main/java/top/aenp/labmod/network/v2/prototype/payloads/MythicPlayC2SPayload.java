package top.aenp.labmod.network.v2.prototype.payloads;

import net.minecraft.network.packet.CustomPayload;
import top.aenp.labmod.network.v2.prototype.injections.ServerPlayNetworkHandlerMethodInjections;

public interface MythicPlayC2SPayload extends CustomPayload {
    void handle(ServerPlayNetworkHandlerMethodInjections handler);
}
