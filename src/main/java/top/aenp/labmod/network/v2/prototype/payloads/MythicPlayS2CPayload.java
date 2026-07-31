package top.aenp.labmod.network.v2.prototype.payloads;

import net.minecraft.network.packet.CustomPayload;
import top.aenp.labmod.network.v2.prototype.injections.ClientPlayNetworkHandlerMethodInjections;

public interface MythicPlayS2CPayload extends CustomPayload {
    void handle(ClientPlayNetworkHandlerMethodInjections handler);
}
