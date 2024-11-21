package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    private Validator validator;
    @BeforeEach
    void makeValidator() {
        validator = new Validator();
    }

    @Test
    void testStringSchema() {
        var schema = validator.string();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));

        schema.required();
        assertTrue(schema.isValid("Susume"));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));

        schema.minLength(7);
        assertFalse(schema.isValid("Su"));
        assertTrue(schema.isValid("Tatakae"));

        schema.minLength(2);
        assertTrue(schema.isValid("Susume"));
        assertTrue(schema.isValid("Su"));

        schema.contains("ata");
        assertTrue(schema.isValid("Tatakae"));
        assertFalse(schema.isValid("FoosBar"));
    }
}