package top.aenp.labmod.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(Explosion.class)
public class ExplosionMixin {
    @Unique private TntEntity causingTntEntity = null;
    @Unique private boolean isCausedByTnt = false;
    @Inject(method = "<init>(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;Lnet/minecraft/world/explosion/ExplosionBehavior;DDDFZLnet/minecraft/world/explosion/Explosion$DestructionType;Lnet/minecraft/particle/ParticleEffect;Lnet/minecraft/particle/ParticleEffect;Lnet/minecraft/registry/entry/RegistryEntry;)V", at = @At(value = "TAIL"))
    private void determineTntExplosion(World world, Entity entity, DamageSource damageSource, ExplosionBehavior behavior, double x, double y, double z, float power, boolean createFire, Explosion.DestructionType destructionType, ParticleEffect particle, ParticleEffect emitterParticle, RegistryEntry<SoundEvent> soundEvent, CallbackInfo ci) {
        if (entity instanceof TntEntity tntEntity) {
            this.causingTntEntity = tntEntity;
            this.isCausedByTnt = true;
        }
    }
    @WrapOperation(method = "collectBlocksAndDamageEntities", at = @At(value = "INVOKE", target = "Ljava/util/Set;add(Ljava/lang/Object;)Z"))
    private <E> boolean filterTntBlocks(Set<E> instance, E e, Operation<Boolean> original, @Local BlockState blockState) {
        if (!instance.contains(e)) {
            if (this.isCausedByTnt) {
                if (blockState != null) {
                    if (blockState.getBlock() instanceof TntBlock) {
                        int currentRemainingChainLength = this.causingTntEntity.labMod$getRemainingChainLength() - 1;
                        if (currentRemainingChainLength > 0 && this.causingTntEntity.labmod$canIgnite()) {
                            this.causingTntEntity.labmod$onIgnition();
                            return original.call(instance, e);
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return original.call(instance, e);
    }
}
