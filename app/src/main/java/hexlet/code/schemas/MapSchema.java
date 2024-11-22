package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private Map<String, BaseSchema<String>> nestedSchemas = new HashMap<>();
    public MapSchema required() {
        addRule("required", r -> r != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        addRule("size", r -> r.size() == size);
        return this;
    }

    public void shape(Map<String, BaseSchema<String>> schemas) {
        nestedSchemas.putAll(schemas);
    }

    @Override
    public boolean isValid(Map<?, ?> value) {
        if (nestedSchemas.isEmpty()) {
            return super.isValid(value);
        }

        if (!super.isValid(value)) {
            return false;
        }

        for (var entry : value.entrySet()) {
            String key = (String) entry.getKey();
            if (nestedSchemas.containsKey(key)) {
                var schema = nestedSchemas.get(key);
                if (!schema.isValid((String) entry.getValue())) {
                    return false;
                }
            }
        }
        return true;
    }
}
