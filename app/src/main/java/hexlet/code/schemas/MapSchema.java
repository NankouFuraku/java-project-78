package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        addRule("required", r -> r != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        addRule("size", r -> r.size() == size);
        return this;
    }

    public void shape(Map<String, BaseSchema<String>> schemas) {
        for (var entry : schemas.entrySet()) {
            String key = entry.getKey();
            BaseSchema<String> schema = entry.getValue();

            addRule(key, value -> {
                if (value.containsKey(key)) {
                    return schema.isValid((String) value.get(key));
                }

                return true;
            });
        }
    }
}
