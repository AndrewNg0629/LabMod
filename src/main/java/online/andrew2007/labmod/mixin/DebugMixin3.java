package online.andrew2007.labmod.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Decoder;
import net.minecraft.registry.MutableRegistry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryLoader;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.entry.RegistryEntryInfo;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceFinder;
import net.minecraft.util.Identifier;
import online.andrew2007.labmod.LabMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.IOException;
import java.io.Reader;

@Mixin(RegistryLoader.class)
public class DebugMixin3 {
    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/RegistryLoader;parseAndAdd(Lnet/minecraft/registry/MutableRegistry;Lcom/mojang/serialization/Decoder;Lnet/minecraft/registry/RegistryOps;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/resource/Resource;Lnet/minecraft/registry/entry/RegistryEntryInfo;)V"), method = "loadFromResource(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/registry/RegistryOps$RegistryInfoGetter;Lnet/minecraft/registry/MutableRegistry;Lcom/mojang/serialization/Decoder;Ljava/util/Map;)V")
    private static <E> void parseAndAdd(MutableRegistry<E> registry, Decoder<E> decoder, RegistryOps<JsonElement> ops, RegistryKey<E> key, Resource resource, RegistryEntryInfo entryInfo, @Local String str, @Local Identifier id, @Local ResourceFinder finder) throws IOException {
        Reader reader = resource.getReader();
        try {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            DataResult<E> dataResult = decoder.parse(ops, jsonElement);
            E object = dataResult.getOrThrow();
            registry.add(key, object, entryInfo);
        } catch (Throwable e1) {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Throwable e2) {
                    e1.addSuppressed(e2);
                }
            }
            throw e1;
        }
        reader.close();
        LabMod.LOGGER.info("{}\n{}\n{}", str, finder.toResourceId(id), entryInfo);
    }
}
