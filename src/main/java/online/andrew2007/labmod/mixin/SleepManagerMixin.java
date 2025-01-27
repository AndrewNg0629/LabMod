package online.andrew2007.labmod.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.SleepManager;
import online.andrew2007.labmod.LabMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(SleepManager.class)
public class SleepManagerMixin {
    @Inject(at = @At(value = "HEAD"), method = "update")
    private void update(List<ServerPlayerEntity> players, CallbackInfoReturnable<Boolean> info) {
        players.removeIf(serverPlayerEntity -> serverPlayerEntity.getDataTracker().get(LabMod.IS_FAKE));
    }
}
