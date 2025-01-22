package online.andrew2007.labmod.mwtConfigSystemProto;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import net.minecraft.util.Identifier;

public interface CustomJsonDeserializer<T> extends JsonDeserializer<T> {
    default boolean readBoolean(JsonElement jsonElement) throws JsonParseException {
        JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();
        if (!jsonPrimitive.isBoolean()) {
            throw new JsonParseException("Wrong type: non boolean value!");
        }
        return jsonPrimitive.getAsBoolean();
    }
    default Identifier parseItemIdentifier(JsonElement jsonElement) throws JsonParseException {
        String rawIdentity = jsonElement.getAsString();

        return null;
    }
}
