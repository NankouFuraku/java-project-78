package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema required() {
        addRule("required", r -> r != null);
        return this;
    }

    public MapSchema size(int size) {
        addRule("size", r -> r.size() == size);
        return this;
    }
}