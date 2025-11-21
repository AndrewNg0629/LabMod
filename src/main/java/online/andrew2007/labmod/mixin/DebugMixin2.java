package online.andrew2007.labmod.mixin;

import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.world.level.LevelInfo;
import net.minecraft.world.level.LevelProperties;
import online.andrew2007.labmod.LabMod;
import online.andrew2007.labmod.ReflectionCenter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;


@Mixin(LevelProperties.class)
public class DebugMixin2 {
    @ModifyVariable(at = @At(value = "HEAD"), argsOnly = true, method = "<init>(Lnet/minecraft/nbt/NbtCompound;ZLnet/minecraft/util/math/BlockPos;FJJIIIZIZZZLnet/minecraft/world/border/WorldBorder$Properties;IILjava/util/UUID;Ljava/util/Set;Ljava/util/Set;Lnet/minecraft/world/timer/Timer;Lnet/minecraft/nbt/NbtCompound;Lnet/minecraft/entity/boss/dragon/EnderDragonFight$Data;Lnet/minecraft/world/level/LevelInfo;Lnet/minecraft/world/gen/GeneratorOptions;Lnet/minecraft/world/level/LevelProperties$SpecialProperty;Lcom/mojang/serialization/Lifecycle;)V")
    private static LevelInfo featureControl(LevelInfo levelInfo) {
        FeatureSet featureSet = levelInfo.getDataConfiguration().enabledFeatures();
        ReflectionCenter.setFieldValue(ReflectionCenter.featuresMask, featureSet, 3L);
        return levelInfo;
    }
}
