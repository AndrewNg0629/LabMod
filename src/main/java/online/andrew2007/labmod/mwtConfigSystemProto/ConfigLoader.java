package online.andrew2007.labmod.mwtConfigSystemProto;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import online.andrew2007.labmod.LabMod;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class ConfigLoader {
    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(ModConfig.class, new ModConfig.Deserializer())
            .registerTypeAdapter(ModIdValidationConfig.class, new ModIdValidationConfig.Deserializer())
            .registerTypeAdapter(BinaryToggleTweaksConfig.class, new BinaryToggleTweaksConfig.Deserializer())
            .registerTypeAdapter(ParamsRequiredTweaksConfig.class, new ParamsRequiredTweaksConfig.Deserializer())
            .registerTypeAdapter(ParamsRequiredTweaksConfig.AutoDiscardingFireBallConfig.class, new ParamsRequiredTweaksConfig.AutoDiscardingFireBallConfig.Deserializer())
            .registerTypeAdapter(ParamsRequiredTweaksConfig.StuffedShulkerBoxStackLimitConfig.class, new ParamsRequiredTweaksConfig.StuffedShulkerBoxStackLimitConfig.Deserializer())
            .registerTypeAdapter(ParamsRequiredTweaksConfig.ShulkerBoxNestingLimitConfig.class, new ParamsRequiredTweaksConfig.ShulkerBoxNestingLimitConfig.Deserializer())
            .registerTypeAdapter(ParamsRequiredTweaksConfig.WardenAttributesWeakeningConfig.class, new ParamsRequiredTweaksConfig.WardenAttributesWeakeningConfig.Deserializer())
            .registerTypeAdapter(ParamsRequiredTweaksConfig.WardenSonicBoomWeakeningConfig.class, new ParamsRequiredTweaksConfig.WardenSonicBoomWeakeningConfig.Deserializer())
            .registerTypeAdapter(ItemEditorConfig.class, new ItemEditorConfig.Deserializer())
            .registerTypeAdapter(ItemEditorConfig.ItemEditorConfigUnit.class, new ItemEditorConfig.ItemEditorConfigUnit.Deserializer())
            .registerTypeAdapter(ItemEditorConfig.FoodProperty.class, new ItemEditorConfig.FoodProperty.Deserializer())
            .registerTypeAdapter(ItemEditorConfig.FoodStatusEffectUnit.class, new ItemEditorConfig.FoodStatusEffectUnit.Deserializer())
            .setPrettyPrinting()
            .create();
    private static boolean initState = false;
    private static ImmutableMap<Identifier, Item> allItems;
    private static ImmutableMap<Identifier, RegistryEntry<StatusEffect>> allStatusEffects;

    public static void onModInit() {
        if (!initState) {
            LabMod.LOGGER.info("Config system initialization starts!");
            initState = true;

            ImmutableMap.Builder<Identifier, Item> itemMapBuilder = ImmutableMap.builder();
            for (Item item : Registries.ITEM) {
                itemMapBuilder.put(Registries.ITEM.getId(item), item);
            }
            allItems = itemMapBuilder.build();

            ImmutableMap.Builder<Identifier, RegistryEntry<StatusEffect>> effectMapBuilder = ImmutableMap.builder();
            for (StatusEffect statusEffect : Registries.STATUS_EFFECT) {
                effectMapBuilder.put(Objects.requireNonNull(Registries.STATUS_EFFECT.getId(statusEffect), "Error initializing config system!"),
                        Registries.STATUS_EFFECT.getEntry(statusEffect)
                );
            }
            allStatusEffects = effectMapBuilder.build();
        }
    }

    @NotNull
    public static ImmutableMap<Identifier, Item> getAllItems() throws IllegalStateException {
        if (!initState) {
            throw new IllegalStateException("The config system hasn't been initialized, allItems is empty.");
        }
        return allItems;
    }

    @NotNull
    public static ImmutableMap<Identifier, RegistryEntry<StatusEffect>> getAllStatusEffects() throws IllegalStateException {
        if (!initState) {
            throw new IllegalStateException("The config system hasn't been initialized, allStatusEffects is empty.");
        }
        return allStatusEffects;
    }
}
