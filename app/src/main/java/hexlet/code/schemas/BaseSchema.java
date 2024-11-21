package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema {
    private Map<String, Predicate<Object>> schemaRules = new HashMap<>();

    void addRule(String name, Predicate<Object> rule) {
        schemaRules.put(name, rule);
    }
    public boolean isValid(Object value) {
        for (var rule : schemaRules.entrySet()) {
            if (!rule.getValue().test(value)) {
                return false;
            }
        }

        return true;
    }
}
