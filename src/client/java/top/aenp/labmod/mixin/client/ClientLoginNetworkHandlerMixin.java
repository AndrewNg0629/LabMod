package top.aenp.labmod.mixin.client;

import net.minecraft.client.network.ClientLoginNetworkHandler;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.c2s.login.LoginQueryResponseC2SPacket;
import net.minecraft.network.packet.s2c.login.LoginQueryRequestS2CPacket;
import net.minecraft.text.Text;
import top.aenp.labmod.LabMod;
import top.aenp.labmod.config.v2.prototype.ConfigManager;
import top.aenp.labmod.network.v2.prototype.payloads.*;
import top.aenp.labmod.network.v2.prototype.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.aenp.labmod.network.v2.prototype.injections.ClientLoginNetworkHandlerMethodInjections;
import top.aenp.labmod.network.v2.prototype.payloads.interfaces.MythicLoginS2CPayload;

@Mixin(value = ClientLoginNetworkHandler.class, priority = 990)
public class ClientLoginNetworkHandlerMixin implements ClientLoginNetworkHandlerMethodInjections {
    @Shadow
    @Final
    private ClientConnection connection;

    @Override
    public void labmod$onModVersion(LoginModVersionS2CPayload version) {
        if (ConfigManager.getConfig().multiplayerSupportEnabled()) {
            LabMod.LOGGER.info("Server initiated negotiation.");
            if (MythicNetwork.NETWORK_COMPATIBLE_VERSIONS.contains(version.modVersion())) {
                this.connection.send(new LoginQueryResponseC2SPacket(MythicNetwork.QUERY_ID, new LoginModVersionC2SPayload(LabMod.MOD_VERSION)));
            } else {
                String message = "Incompatible server version: " + version.modVersion();
                this.connection.disconnect(Text.of(message));
                LabMod.LOGGER.warn(message);
            }
        } else {
            LabMod.LOGGER.warn("You didn't enable multiplayer support, you may be kicked by the server.");
        }
    }

    @Override
    public void labmod$onModIdRequest() {
        this.connection.send(new LoginQueryResponseC2SPacket(MythicNetwork.QUERY_ID, new LoginModIdListC2SPayload(MythicNetwork.ALL_MODS.asList())));
    }

    @Override
    public void labmod$onConfigPush(NetworkSyncedConfig config) {
        ConfigManager.getInstance().onConfigPush(config);
    }

    @Inject(method = "onQueryRequest", at = @At(value = "HEAD"), cancellable = true)
    private void handleRequest(LoginQueryRequestS2CPacket packet, CallbackInfo info) {
        if (packet.queryId() == MythicNetwork.QUERY_ID) {
            MythicLoginS2CPayload payload = (MythicLoginS2CPayload) packet.payload();
            payload.handle(this);
            info.cancel();
        }
    }
}
