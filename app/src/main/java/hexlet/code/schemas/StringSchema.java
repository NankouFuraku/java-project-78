package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    public void required() {
        addRule("required", (r -> r instanceof String && !r.equals("")));
    }

    public void minLength(int length) {
        addRule("minLength", (r -> r instanceof String && ((String) r).length() >= length));
    }

    public void contains(String sequence) {
        addRule("contains", (r -> r instanceof String && ((String) r).contains(sequence)));
    }
}
