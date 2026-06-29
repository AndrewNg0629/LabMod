package top.aenp.labmod.network.v2.prototype;

import net.minecraft.network.packet.CustomPayload;

public interface MythicPlayS2CPayload extends CustomPayload {
    void handle(ClientPlayNetworkHandlerMethodInjections handler);
}
