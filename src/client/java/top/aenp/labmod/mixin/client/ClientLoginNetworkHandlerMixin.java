package top.aenp.labmod.mixin.client;

import net.minecraft.client.network.ClientLoginNetworkHandler;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.c2s.login.LoginQueryResponseC2SPacket;
import net.minecraft.network.packet.s2c.login.LoginQueryRequestS2CPacket;
import top.aenp.labmod.LabMod;
import top.aenp.labmod.network.v2.prototype.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ClientLoginNetworkHandler.class, priority = 990)
public class ClientLoginNetworkHandlerMixin implements ClientLoginNetworkHandlerMethodInjections {
    @Shadow
    @Final
    private ClientConnection connection;

    @Inject(method = "onQueryRequest", at = @At(value = "HEAD"), cancellable = true)
    private void handleRequest(LoginQueryRequestS2CPacket packet, CallbackInfo info) {
        if (packet.queryId() == MythicNetwork.QUERY_ID) {
            MythicLoginS2CPayload payload = (MythicLoginS2CPayload) packet.payload();
            payload.handle(this);
            info.cancel();
        }
    }

    @Override
    public void labmod$onTestLoginS2C(TestLoginS2CPayload payload) {
        LabMod.LOGGER.info("Client received: {}", payload.hello());
        this.connection.send(new LoginQueryResponseC2SPacket(MythicNetwork.QUERY_ID, new TestLoginC2SPayload("Hello server!")));
        this.connection.send(new LoginQueryResponseC2SPacket(MythicNetwork.QUERY_ID, new TestLoginC2SPayload("Hello server!")));
    }
}
