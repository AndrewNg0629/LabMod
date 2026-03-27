package online.andrew2007.labmod.mixin;

import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.c2s.login.LoginHelloC2SPacket;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import online.andrew2007.labmod.LabMod;
import online.andrew2007.labmod.network.v2.prototype.CustomC2SPacket;
import online.andrew2007.labmod.network.v2.prototype.CustomS2CPacket;
import online.andrew2007.labmod.network.v2.prototype.ServerLoginNetworkHandlerInjection;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLoginNetworkHandler.class)
public class ServerLoginNetworkHandlerMixin implements ServerLoginNetworkHandlerInjection {
    @Shadow @Final ClientConnection connection;

    @Unique
    private boolean testDone = false;

    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void tick(CallbackInfo info) {
        if (!this.testDone) {
            this.connection.send(new CustomS2CPacket("Hello world!"));
            this.testDone = true;
        }
    }

    @Override
    public void labmod$onCustomC2SPacket(CustomC2SPacket packet) {
        LabMod.LOGGER.info("Server received data: {}", packet.data);
    }
}
