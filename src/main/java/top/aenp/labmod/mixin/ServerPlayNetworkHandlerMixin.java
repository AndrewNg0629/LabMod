package top.aenp.labmod.mixin;

import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.c2s.common.CustomPayloadC2SPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ConnectedClientData;
import net.minecraft.server.network.ServerCommonNetworkHandler;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.aenp.labmod.LabMod;
import top.aenp.labmod.network.v2.prototype.payloads.MythicPlayC2SPayload;
import top.aenp.labmod.network.v2.prototype.injections.ServerPlayNetworkHandlerMethodInjections;
import top.aenp.labmod.network.v2.prototype.test.TestPlayC2SPayload;

@Mixin(value = ServerPlayNetworkHandler.class, priority = 990)
public abstract class ServerPlayNetworkHandlerMixin extends ServerCommonNetworkHandler implements ServerPlayNetworkHandlerMethodInjections {
    public ServerPlayNetworkHandlerMixin(MinecraftServer server, ClientConnection connection, ConnectedClientData clientData) {
        super(server, connection, clientData);
    }
    @Inject(method = "onCustomPayload", at = @At(value = "HEAD"), cancellable = true)
    private void handleMythicPayloads(CustomPayloadC2SPacket packet, CallbackInfo info) {
        CustomPayload payload = packet.payload();
        if (payload instanceof MythicPlayC2SPayload mythicPayload) {
            mythicPayload.handle(this);
            info.cancel();
        }
    }
    @Override
    public void labmod$onCustomC2S(TestPlayC2SPayload payload) {
        LabMod.LOGGER.info("Server play handler received: {}", payload.data());
    }
}
