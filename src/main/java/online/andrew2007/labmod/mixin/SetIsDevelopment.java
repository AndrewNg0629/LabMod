package online.andrew2007.labmod.mixin;

import net.minecraft.SharedConstants;
import net.minecraft.server.Main;
import online.andrew2007.labmod.EnvironmentDetection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Main.class)
public class SetIsDevelopment {
    @Inject(at = @At(value = "HEAD"), method = "main")
    private static void setIsDevelopment(String[] args, CallbackInfo info) {
        if (EnvironmentDetection.isYarn) {
            SharedConstants.isDevelopment = true;
        }
    }
}
