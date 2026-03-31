package online.andrew2007.labmod.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.authlib.GameProfile;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import online.andrew2007.labmod.LabMod;
import online.andrew2007.labmod.ReflectionUtils;
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
@SuppressWarnings("DataFlowIssue")
public abstract class ServerLoginNetworkHandlerMixin implements ServerLoginNetworkHandlerInjection {
    @Shadow @Final ClientConnection connection;

    @Shadow protected abstract void sendSuccessPacket(GameProfile profile);

    @Shadow private int loginTicks;
    @Unique
    private boolean modNegotiating = false;

    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void tick(CallbackInfo info) {
        if (this.modNegotiating) {
            this.loginTicks--;
        }
    }

    @Override
    public void labmod$onCustomC2SPacket(CustomC2SPacket packet) {
        LabMod.LOGGER.info("Server received data: {}", packet.data);
    }

    @Inject(method = "tickVerify",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;disconnectDuplicateLogins(Lcom/mojang/authlib/GameProfile;)Z"),
            cancellable = true
    )
    private void onTickVerify(GameProfile profile, CallbackInfo info, @Local PlayerManager playerManager) {
        ReflectionUtils.setLoginHandlerState((ServerLoginNetworkHandler) (Object) this, 3);
        this.modNegotiating = true;
        new Thread(() -> {
            this.connection.send(new CustomS2CPacket("crash"));
            this.modNegotiating = false;
            /*
            if (!playerManager.disconnectDuplicateLogins(profile)) {
                ReflectionUtils.setLoginHandlerState((ServerLoginNetworkHandler) (Object) this, 5);
            } else {
                this.sendSuccessPacket(profile);
            }

             */
        }, "LabMod Login Negotiation Thread").start();
        info.cancel();
    }
}
