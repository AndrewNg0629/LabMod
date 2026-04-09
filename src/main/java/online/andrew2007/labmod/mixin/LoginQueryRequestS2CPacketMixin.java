package online.andrew2007.labmod.mixin;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.login.LoginQueryRequestPayload;
import net.minecraft.network.packet.s2c.login.LoginQueryRequestS2CPacket;
import net.minecraft.util.Identifier;
import online.andrew2007.labmod.network.v2.prototype.ModVersionExchangePayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = LoginQueryRequestS2CPacket.class, priority = 999)
public class LoginQueryRequestS2CPacketMixin {
    @Inject(method = "readPayload", at = @At(value = "HEAD"), cancellable = true)
    private static void readPayload(Identifier id, PacketByteBuf buf, CallbackInfoReturnable<LoginQueryRequestPayload> info) {
        if (ModVersionExchangePayload.ID.equals(id)) {
            String modVersion = buf.readString();
            info.setReturnValue(new ModVersionExchangePayload(modVersion));
        }
    }
}
