package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    public void required() {
        addRule("required", r -> r instanceof Integer);
    }

    public void positive() {
        addRule("positive", r -> r instanceof Integer && ((int) r) > 0);
    }

    public void range(int bottomRange, int topRange) {
        addRule("range", r -> r instanceof Integer && (int) r >= bottomRange && (int) r <= topRange);
    }
}
