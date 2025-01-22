package online.andrew2007.labmod.mwtConfigSystemProto;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Set;

public record ModConfig(
        boolean modEnabled,
        boolean tweaksEnabled,
        boolean ServerPlaySupportEnabled,
        ClasspathValidationConfig classpathValidationConfig,
        BinaryToggleTweaksConfig binaryToggleTweaksConfig,
        ParamsRequiredTweaksConfig paramsRequiredTweaksConfig,
        ItemEditorConfig itemEditorConfig
) {
    public static class Deserializer implements CustomJsonDeserializer<ModConfig> {
        @Override
        public ModConfig deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            if (!jsonObject.keySet().equals(Set.of(
                    "mod_enabled",
                    "tweaks_enabled",
                    "server_play_support_enabled",
                    "classpath_validation",
                    "binary_toggle_tweaks",
                    "params_required_tweaks",
                    "item_editor_config"
            ))) {
                throw new JsonParseException("Wrong config structure, please have a check.");
            }
            ClasspathValidationConfig classpathValidationConfig = context.deserialize(jsonObject.get("classpath_validation"), ClasspathValidationConfig.class);
            BinaryToggleTweaksConfig binaryToggleTweaksConfig = context.deserialize(jsonObject.get("binary_toggle_tweaks"), BinaryToggleTweaksConfig.class);
            ParamsRequiredTweaksConfig paramsRequiredTweaksConfig = context.deserialize(jsonObject.get("params_required_tweaks"), ParamsRequiredTweaksConfig.class);
            ItemEditorConfig itemEditorConfig = context.deserialize(jsonObject.get("item_editor_config"), ItemEditorConfig.class);
            return new ModConfig(
                    readBoolean(jsonObject.get("mod_enabled")),
                    readBoolean(jsonObject.get("tweaks_enabled")),
                    readBoolean(jsonObject.get("server_play_support_enabled")),
                    classpathValidationConfig,
                    binaryToggleTweaksConfig,
                    paramsRequiredTweaksConfig,
                    itemEditorConfig
            );
        }
    }
}
