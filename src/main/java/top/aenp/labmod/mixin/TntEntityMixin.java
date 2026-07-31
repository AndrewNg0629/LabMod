package top.aenp.labmod.mixin;

import net.minecraft.entity.TntEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import top.aenp.labmod.TntEntityMethodInjections;

@Mixin(TntEntity.class)
public class TntEntityMixin implements TntEntityMethodInjections {
    @Unique private int remainingChainLength = 7;

    @Unique private int remainingIgnitions = 20;

    @Override
    public int labMod$getRemainingChainLength() {
        return this.remainingChainLength;
    }

    @Override
    public void labMod$setRemainingChainLength(int chain) {
        this.remainingChainLength =chain;
    }

    @Override
    public boolean labmod$canIgnite() {
        return this.remainingIgnitions > 0;
    }

    @Override
    public void labmod$onIgnition() {
        this.remainingIgnitions--;
    }
}
