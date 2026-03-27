package online.andrew2007.labmod.mixin;

import net.minecraft.network.NetworkStateBuilder;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientLoginPacketListener;
import net.minecraft.network.listener.ServerLoginPacketListener;
import net.minecraft.network.state.LoginStates;
import online.andrew2007.labmod.network.v2.prototype.CustomC2SPacket;
import online.andrew2007.labmod.network.v2.prototype.CustomS2CPacket;
import online.andrew2007.labmod.network.v2.prototype.NetworkPrototype;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LoginStates.class)
public class LoginStatesMixin {
    @Inject(method = "method_56018", at = @At(value = "RETURN"))
    private static void buildS2CFactory(NetworkStateBuilder<ClientLoginPacketListener, PacketByteBuf> builder, CallbackInfo info) {
        builder.add(NetworkPrototype.CUSTOM_S2C, CustomS2CPacket.CODEC);
    }
    @Inject(method = "method_56019", at = @At(value = "RETURN"))
    private static void buildC2SFactory(NetworkStateBuilder<ServerLoginPacketListener, PacketByteBuf> builder, CallbackInfo info) {
        builder.add(NetworkPrototype.CUSTOM_C2S, CustomC2SPacket.CODEC);
    }
}
