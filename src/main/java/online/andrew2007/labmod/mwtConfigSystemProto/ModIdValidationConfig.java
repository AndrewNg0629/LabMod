package online.andrew2007.labmod.mwtConfigSystemProto;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

public record ModIdValidationConfig(boolean enabled, String[] modIdList) {
    public static class Deserializer implements CustomJsonDeserializer<ModIdValidationConfig> {
        @Override
        public ModIdValidationConfig deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            if (!jsonObject.keySet().equals(Set.of("enabled", "mod_id_list"))) {
                throw new JsonParseException("Wrong config structure, please have a check.");
            }
            List<JsonElement> modIdJsonList = jsonObject.get("mod_id_list").getAsJsonArray().asList();
            String[] modIdList = new String[modIdJsonList.size()];
            for (int i = 0; i < modIdJsonList.size(); i++) {
                modIdList[i] = modIdJsonList.get(i).getAsString();
            }
            return new ModIdValidationConfig(readBoolean(jsonObject.get("enabled")), modIdList);
        }
    }
}
