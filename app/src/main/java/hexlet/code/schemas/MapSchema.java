package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        addRule("required", r -> r != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        addRule("size", r -> r.size() == size);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addRule(
                "shape",
                map -> schemas.entrySet().stream().allMatch(e -> {
                    var v = map.get(e.getKey());
                    var schema = e.getValue();
                    return schema.isValid((T) v);
                })
        );
        return this;
    }
}
