package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class StringSchema implements Schema {
    private Map<String, Predicate> schemaRules = new HashMap<>();
    @Override
    public boolean isValid(Object value) {
        for (var rule : schemaRules.entrySet()) {
            if (!rule.getValue().test(value)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void required() {
        schemaRules.put("required", (r -> r instanceof String && !r.equals("")));
    }

    public void minLength(int length) {
        schemaRules.put("minLength", (r -> ((String) r).length() >= length));
    }

    public void contains(String sequence) {
        schemaRules.put("contains", (r -> ((String) r).contains(sequence)));
    }
}
