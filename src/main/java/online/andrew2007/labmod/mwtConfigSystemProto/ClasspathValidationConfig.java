package online.andrew2007.labmod.mwtConfigSystemProto;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

public record ClasspathValidationConfig(boolean enabled, String[] classPathList) {
    public static class Deserializer implements CustomJsonDeserializer<ClasspathValidationConfig> {
        @Override
        public ClasspathValidationConfig deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            if (!jsonObject.keySet().equals(Set.of("enabled", "classpath_list"))) {
                throw new JsonParseException("Wrong config structure, please have a check.");
            }
            List<JsonElement> classpathJsonList = jsonObject.get("classpath_list").getAsJsonArray().asList();
            String[] classPathList = new String[classpathJsonList.size()];
            for (int i = 0; i < classpathJsonList.size(); i++) {
                classPathList[i] = classpathJsonList.get(i).getAsString();
            }
            return new ClasspathValidationConfig(readBoolean(jsonObject.get("enabled")), classPathList);
        }
    }
}
