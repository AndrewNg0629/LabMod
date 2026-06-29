package top.aenp.labmod.mixin;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.s2c.common.CustomPayloadS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.aenp.labmod.network.v2.prototype.TestCommonS2CPayload;

import java.util.ArrayList;

@Mixin(value = CustomPayloadS2CPacket.class, priority = 990)
public class CustomPayloadS2CPacketMixin {
    @Inject(method = "method_58270", at = @At(value = "HEAD"))
    private static void supplyMythicTypes(ArrayList<CustomPayload.Type<? super RegistryByteBuf, ?>> types, CallbackInfo info) {
        types.add(new CustomPayload.Type<>(TestCommonS2CPayload.ID, TestCommonS2CPayload.CODEC));
    }
}
