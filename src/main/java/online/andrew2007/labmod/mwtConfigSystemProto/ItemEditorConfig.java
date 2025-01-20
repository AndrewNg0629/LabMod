package online.andrew2007.labmod.mwtConfigSystemProto;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.lang.reflect.Type;
import java.util.Set;

public record ItemEditorConfig(boolean enabled) {
    static {
        ImmutableMap.Builder<String, Item> builder = ImmutableMap.builder();
        for (Item item : Registries.ITEM) {
            builder.put(Registries.ITEM.getEntry(item).getIdAsString(), item);
        }
        allItems = builder.build();
    }
    public static final ImmutableMap<String, Item> allItems;
    public static record ItemEditorConfigUnit(
            Item targetItem,
            int maxStackSize,
            int maxDamage,
            Rarity rarity
    ) {

    }
    public record FoodStatusEffectUnit(StatusEffect statusEffect, int level, int lastingTime, int probability) {
        public static class Deserializer implements CustomJsonDeserializer<FoodStatusEffectUnit> {
            @Override
            public FoodStatusEffectUnit deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                JsonObject jsonObject = json.getAsJsonObject();
                if (!jsonObject.keySet().equals(Set.of("status_effect", "level", "lasting_time", "probability"))) {
                    throw new JsonParseException("Wrong config structure, please have a check.");
                }

                return null;
            }
        }
    }
}
