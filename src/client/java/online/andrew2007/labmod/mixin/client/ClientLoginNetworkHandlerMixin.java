package online.andrew2007.labmod.mixin.client;

import net.minecraft.client.network.ClientLoginNetworkHandler;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.s2c.login.LoginQueryRequestPayload;
import net.minecraft.network.packet.s2c.login.LoginQueryRequestS2CPacket;
import net.minecraft.text.Text;
import online.andrew2007.labmod.LabMod;
import online.andrew2007.labmod.network.v2.prototype.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ClientLoginNetworkHandler.class, priority = 999)
public class ClientLoginNetworkHandlerMixin implements ClientLoginNetworkHandlerInjection {
    @Shadow @Final private ClientConnection connection;

    @Override
    public void labmod$onCustomS2CPacket(CustomS2CPacket packet) {
        LabMod.LOGGER.info("Received data: {}", packet.data);
        if ("crash".equals(packet.data)) {
            throw new RuntimeException("Debug crash.");
        }
    }

    @Override
    public void labmod$onNegotiationStartS2CPacket(NegotiationStartS2CPacket packet) {
        LabMod.LOGGER.info("The server has LabMod{} installed and initiated negotiation.", packet.serverModVersion);
        if (!LabMod.NETWORK_COMPATIBLE_VERSIONS.contains(packet.serverModVersion)) {
            this.connection.disconnect(Text.of(String.format("Incompatible network version. Server: %s, Client: %s", packet.serverModVersion, LabMod.MOD_VERSION)));
            LabMod.LOGGER.error("Disconnected due to incompatible server network protocol version: {}. Expecting: {}", packet.serverModVersion, LabMod.NETWORK_COMPATIBLE_VERSIONS);
        } else {
            //this.connection.send();
        }
    }

    @Inject(method = "onQueryRequest", at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V", remap = false, shift = At.Shift.AFTER), cancellable = true)
    private void onQueryRequest(LoginQueryRequestS2CPacket packet, CallbackInfo info) {
        LoginQueryRequestPayload payload = packet.payload();
        if (payload != null) {
            if (payload instanceof ModVersionExchangePayload versionPayload) {
                //TODO: Test logic.
                LabMod.LOGGER.info("Received version number: {}", versionPayload.modVersion());
                //info.cancel();
            }
        }
    }
}
