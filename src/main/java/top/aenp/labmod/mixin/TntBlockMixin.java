package top.aenp.labmod.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.TntEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TntBlock.class)
public class TntBlockMixin {
    @Inject(method = "onDestroyedByExplosion", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/TntEntity;setFuse(I)V", shift = At.Shift.AFTER), cancellable = true)
    private void applyChain(World world, BlockPos pos, Explosion explosion, CallbackInfo info, @Local TntEntity tntEntity) {
        Entity causingEntity = explosion.getEntity();
        if (causingEntity instanceof TntEntity causingTntEntity) {
            tntEntity.labMod$setRemainingChainLength(causingTntEntity.labMod$getRemainingChainLength() - 1);
        }
    }
}
