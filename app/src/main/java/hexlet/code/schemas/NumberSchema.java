package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema required() {
        addRule("required", r -> r != null);
        return this;
    }

    public NumberSchema positive() {
        addRule("positive", r -> r != null && r > 0);
        return this;
    }

    public NumberSchema range(int bottomRange, int topRange) {
        addRule("range", r -> r != null && r >= bottomRange && r <= topRange);
        return this;
    }
}
