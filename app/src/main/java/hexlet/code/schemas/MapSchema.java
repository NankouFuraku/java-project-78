package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    public void required() {
        addRule("required", r -> r instanceof Map<?,?>);
    }

    public void size(int size) {
        addRule("size", r -> ((Map<?, ?>) r).size() == size);
    }
}
