package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    public StringSchema required() {
        addRule("required", (r -> r != null && !r.isEmpty()));
        return this;
    }

    public StringSchema minLength(int length) {
        addRule("minLength", (r -> r != null && r.length() >= length));
        return this;
    }

    public StringSchema contains(String sequence) {
        addRule("contains", (r -> r != null && r.contains(sequence)));
        return this;
    }
}
