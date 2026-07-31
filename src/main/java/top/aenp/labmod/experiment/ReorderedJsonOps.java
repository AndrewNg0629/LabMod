package top.aenp.labmod.experiment;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.RecordBuilder;
import java.util.List;
import java.util.Map;

//F**k Mojang RecordCodecBuilder!
public class ReorderedJsonOps extends JsonOps {
    public static final ReorderedJsonOps INSTANCE = new ReorderedJsonOps(false);

    private static final int[][] reorderingMaps = {
            {0},
            {0, 1},
            {0, 1, 2},
            {0, 1, 2, 3},
            {3, 4, 0, 1, 2},
            {3, 4, 5, 0, 1, 2},
            {4, 5, 6, 0, 1, 2, 3},
            {4, 5, 6, 7, 0, 1, 2, 3},
            {5, 6, 7, 8, 3, 4, 0, 1, 2},
            {8, 9, 5, 6, 7, 3, 4, 0, 1, 2},
            {9, 10, 6, 7, 8, 3, 4, 5, 0, 1, 2},
            {9, 10, 11, 6, 7, 8, 3, 4, 5, 0, 1, 2},
            {10, 11, 12, 7, 8, 9, 4, 5, 6, 0, 1, 2, 3},
            {11, 12, 13, 7, 8, 9, 10, 4, 5, 6, 0, 1, 2, 3},
            {12, 13, 14, 8, 9, 10, 11, 4, 5, 6, 7, 0, 1, 2, 3},
            {12, 13, 14, 15, 8, 9, 10, 11, 4, 5, 6, 7, 0, 1, 2, 3}
    };

    protected ReorderedJsonOps(boolean compressed) {
        super(compressed);
    }

    @Override
    public RecordBuilder<JsonElement> mapBuilder() {
        return new ReorderedJsonRecordBuilder();
    }

    private class ReorderedJsonRecordBuilder extends RecordBuilder.AbstractStringBuilder<JsonElement, JsonObject> {
        protected ReorderedJsonRecordBuilder() {
            super(ReorderedJsonOps.this);
        }

        @Override
        protected JsonObject initBuilder() {
            return new JsonObject();
        }

        @Override
        protected JsonObject append(final String key, final JsonElement value, final JsonObject builder) {
            builder.add(key, value);
            return builder;
        }

        @Override
        protected DataResult<JsonElement> build(final JsonObject builder, final JsonElement prefix) {
            JsonObject jsonObject = new JsonObject();
            int size = builder.size();
            if (size <= 16) {
                List<String> keyList = builder.keySet().stream().toList();
                int[] reorderingMap = reorderingMaps[size - 1];
                for (int index : reorderingMap) {
                    String key = keyList.get(index);
                    jsonObject.add(key, builder.get(key));
                }
            } else {
                jsonObject = builder;
            }
            if (prefix == null || prefix instanceof JsonNull) {
                return DataResult.success(jsonObject);
            }
            if (prefix instanceof JsonObject) {
                final JsonObject result = new JsonObject();
                for (final Map.Entry<String, JsonElement> entry : prefix.getAsJsonObject().entrySet()) {
                    result.add(entry.getKey(), entry.getValue());
                }
                for (final Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                    result.add(entry.getKey(), entry.getValue());
                }
                return DataResult.success(result);
            }
            return DataResult.error(() -> "mergeToMap called with not a map: " + prefix, prefix);
        }
    }
}
