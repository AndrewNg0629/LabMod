package top.aenp.labmod.mixin;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.c2s.common.CustomPayloadC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.aenp.labmod.network.v2.prototype.TestPlayC2SPayload;

import java.util.ArrayList;

@Mixin(value = CustomPayloadC2SPacket.class, priority = 990)
public class CustomPayloadC2SPacketMixin {
    @Inject(method = "method_58271", at = @At(value = "HEAD"))
    private static void supplyMythicTypes(ArrayList<CustomPayload.Type<? super PacketByteBuf, ?>> types, CallbackInfo info) {
        types.add(new CustomPayload.Type<>(TestPlayC2SPayload.ID, TestPlayC2SPayload.CODEC));
    }
}
