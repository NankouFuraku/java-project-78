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

    @Test
    void testNumberSchema() {
        var schema = validator.number();
        assertTrue(schema.isValid(6));
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(-6));

        schema.required();
        assertTrue(schema.isValid(6));
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(-6));

        schema.positive();
        assertTrue(schema.isValid(6));
        assertFalse(schema.isValid(-6));

        schema.range(3, 9);
        assertTrue(schema.isValid(3));
        assertTrue(schema.isValid(9));
        assertFalse(schema.isValid(1));
        assertFalse(schema.isValid(11));
    }
}