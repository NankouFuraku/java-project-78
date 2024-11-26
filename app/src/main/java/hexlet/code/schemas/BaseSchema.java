package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {
    private Map<String, Predicate<T>> schemaRules = new HashMap<>();

    final void addRule(String name, Predicate<T> rule) {
        schemaRules.put(name, rule);
    }
    public final boolean isValid(T value) {
        for (var rule : schemaRules.entrySet()) {
            if (!rule.getValue().test(value)) {
                return false;
            }
        }

        return true;
    }
}
