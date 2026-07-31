package top.aenp.labmod.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.client.network.ClientConnectionState;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.c2s.common.CustomPayloadC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.aenp.labmod.LabMod;
import top.aenp.labmod.config.v2.prototype.NetworkSyncedConfig;
import top.aenp.labmod.network.v2.prototype.injections.ClientPlayNetworkHandlerMethodInjections;
import top.aenp.labmod.network.v2.prototype.payloads.MythicPlayS2CPayload;
import top.aenp.labmod.network.v2.prototype.test.TestCommonS2CPayload;
import top.aenp.labmod.network.v2.prototype.test.TestPlayC2SPayload;

@Mixin(value = ClientPlayNetworkHandler.class, priority = 990)
public abstract class ClientPlayNetworkHandlerMixin extends ClientCommonNetworkHandler implements ClientPlayNetworkHandlerMethodInjections {
    protected ClientPlayNetworkHandlerMixin(MinecraftClient client, ClientConnection connection, ClientConnectionState connectionState) {
        super(client, connection, connectionState);
    }

    @Inject(method = "onCustomPayload", at = @At(value = "HEAD"), cancellable = true)
    private void handleMythicPayload(CustomPayload payload, CallbackInfo info) {
        if (payload instanceof MythicPlayS2CPayload mythicPayload) {
            mythicPayload.handle(this);
            info.cancel();
        }
    }
    @Override
    public void labmod$onCustomS2C(TestCommonS2CPayload payload) {
        LabMod.LOGGER.info("Client play handler received: {}", payload.data());
        super.sendPacket(new CustomPayloadC2SPacket(new TestPlayC2SPayload("Hello server!")));
    }

    @Override
    public void labmod$onConfigPush(NetworkSyncedConfig config) {

    }
}
