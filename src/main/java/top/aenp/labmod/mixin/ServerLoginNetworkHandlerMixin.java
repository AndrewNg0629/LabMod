package top.aenp.labmod.mixin;

import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.c2s.login.LoginQueryResponseC2SPacket;
import net.minecraft.network.packet.s2c.login.LoginQueryRequestS2CPacket;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import net.minecraft.text.Text;
import top.aenp.labmod.LabMod;
import top.aenp.labmod.network.v2.prototype.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ServerLoginNetworkHandler.class, priority = 999)
public abstract class ServerLoginNetworkHandlerMixin implements ServerLoginNetworkHandlerMethodInjections {
    @Shadow
    @Final
    ClientConnection connection;

    @Shadow
    public abstract void disconnect(Text reason);

    @Unique private boolean testDone = false;
    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void tick(CallbackInfo info) {
        if (!this.testDone) {
            this.testDone = true;
            this.connection.send(new LoginQueryRequestS2CPacket(MythicNetwork.QUERY_ID, new TestLoginS2CPayload("Hello world!")));
        }
    }

    @Inject(method = "onQueryResponse", at = @At(value = "HEAD"), cancellable = true)
    private void handleResponse(LoginQueryResponseC2SPacket packet, CallbackInfo info) {
        if (packet.queryId() == MythicNetwork.QUERY_ID) {
            if (packet.response() != null) {
                MythicLoginC2SPayload payload = (MythicLoginC2SPayload) packet.response();
                payload.handle(this);
            } else {
                this.disconnect(Text.of("Please have labmod installed."));
            }
            info.cancel();
        }
    }

    @Override
    public void labmod$onTestLoginC2S(TestLoginC2SPayload payload) {
        LabMod.LOGGER.info("Server received: {}", payload.hello());
    }
}
