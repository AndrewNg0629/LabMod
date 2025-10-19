package online.andrew2007.labmod.mixin;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import online.andrew2007.labmod.LabMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(JsonDataLoader.class)
public class DebugMixin1 {
    @Inject(at = @At(value = "HEAD"), method = "load")
    private static void load(ResourceManager manager, String dataType, Gson gson, Map<Identifier, JsonElement> results, CallbackInfo info) {
        LabMod.LOGGER.info(dataType);
    }
}
