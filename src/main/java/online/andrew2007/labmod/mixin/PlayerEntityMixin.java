package online.andrew2007.labmod.mixin;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import online.andrew2007.labmod.LabMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;initDataTracker(Lnet/minecraft/entity/data/DataTracker$Builder;)V"), method = "initDataTracker")
    private void initDataTracker(DataTracker.Builder builder, CallbackInfo info) {
        if (((PlayerEntity) (Object) this) instanceof ServerPlayerEntity serverPlayerEntity) {
            builder.add(LabMod.IS_FAKE, LabMod.determineFake(serverPlayerEntity));
        }
    }
}
