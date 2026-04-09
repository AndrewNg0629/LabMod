package online.andrew2007.labmod.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.authlib.GameProfile;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.DisconnectionInfo;
import net.minecraft.network.listener.ServerLoginPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.login.LoginQueryResponseC2SPacket;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import net.minecraft.text.Text;
import online.andrew2007.labmod.LabMod;
import online.andrew2007.labmod.ReflectionUtils;
import online.andrew2007.labmod.network.v2.prototype.CustomC2SPacket;
import online.andrew2007.labmod.network.v2.prototype.ModNegotiationTask;
import online.andrew2007.labmod.network.v2.prototype.ServerLoginNetworkHandlerInjection;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLoginNetworkHandler.class)
@SuppressWarnings("DataFlowIssue")
public abstract class ServerLoginNetworkHandlerMixin implements ServerLoginNetworkHandlerInjection {
    @Shadow @Final ClientConnection connection;
    @Shadow protected abstract void sendSuccessPacket(GameProfile profile);
    @Shadow private int loginTicks;
    @Shadow public abstract void disconnect(Text reason);

    @Unique private ModNegotiationTask negotiationTask;
    @Unique private Thread negotiationThread;


    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void tick(CallbackInfo info) {
        if (ReflectionUtils.isHandlerNegotiating((ServerLoginNetworkHandler) (Object) this)) {
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
        this.negotiationTask = new ModNegotiationTask((ServerLoginNetworkHandler) (Object) this, this.connection, profile, playerManager);
        this.negotiationThread = Thread.startVirtualThread(this.negotiationTask);
        info.cancel();
    }

    @Override
    public void labmod$finishModNegotiation(GameProfile profile, PlayerManager manager) {
        if (!manager.disconnectDuplicateLogins(profile)) {
            ReflectionUtils.setLoginHandlerState((ServerLoginNetworkHandler) (Object) this, 5);
        } else {
            this.sendSuccessPacket(profile);
        }
    }

    @Inject(method = "onDisconnected", at = @At(value = "HEAD"))
    private void handleDisconnection(DisconnectionInfo disconnectionInfo, CallbackInfo info) {
        if (this.negotiationThread != null) {
            this.negotiationTask.disconnected.set(true);
            this.negotiationThread.interrupt();
        }
    }

    @Override
    public void labmod$onNegotiationPackets(Packet<ServerLoginPacketListener> packet) {
        if (this.negotiationThread != null) {
            this.negotiationTask.C2SQueue.add(packet);
            synchronized (this.negotiationTask.lock) {
                this.negotiationTask.lock.notifyAll();
            }
        } else {
            this.disconnect(Text.of("Unexpected packet: " + packet));
        }
    }

    @ModifyArg(method = "onQueryResponse", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerLoginNetworkHandler;disconnect(Lnet/minecraft/text/Text;)V"))
    private Text modifyDisconnectMessage(Text reason, @Local(argsOnly = true) LoginQueryResponseC2SPacket packet) {
        if (ReflectionUtils.isHandlerNegotiating((ServerLoginNetworkHandler) (Object) this) && this.negotiationThread != null && packet.queryId() == Integer.MIN_VALUE) {
            this.negotiationTask.disconnected.set(true);
            this.negotiationThread.interrupt();
            return Text.of("Negotiation failed, please have LabMod installed.");
        }
        return reason;
    }
}
