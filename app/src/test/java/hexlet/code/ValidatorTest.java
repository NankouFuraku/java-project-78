package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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
    public void testStringSchemaChain() {
        var schema = validator.string().required().minLength(7).contains("ata");
        assertTrue(schema.isValid("Tatakae"));
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

    @Test
    void testNumberSchemaChain() {
        var schema = validator.number()
                .required()
                .positive()
                .range(3, 9);
        assertTrue(schema.isValid(5));
    }

    @Test
    void testMapSchema() {
        var schema = validator.map();
        assertTrue(schema.isValid(null));

        schema.required();
        assertFalse(schema.isValid(null));

        var data = new HashMap<String, String>();
        assertTrue(schema.isValid(data));
        data.put("Foo", "Bar");
        assertTrue(schema.isValid(data));

        schema.size(2);
        assertFalse(schema.isValid(data));

        data.put("Foos", "Bars");
        assertTrue(schema.isValid(data));

        data.put("Foobar", "Again");
        assertFalse(schema.isValid(data));
    }

    @Test
    void testMapSchemaChain() {
        var schema = validator.map().required().size(2);
        var data = new HashMap<String, String>();
        data.put("Foo", "Bar");
        data.put("Foos", "Bars");
        assertTrue(schema.isValid(data));
    }

    @Test
    void testNestedMapSchema() {
        var schema = validator.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(5));

        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "Jon");
        human1.put("lastName", "Snow");
        schema.required().size(2);
        assertFalse(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "Jon");
        human2.put("lastName", "Stark");
        assertTrue(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "John");
        human3.put("lastName", null);
        assertFalse(schema.isValid(human3));

        Map<String, String> human4 = new HashMap<>();
        human4.put("firstName", "John");
        human4.put("lastName", null);
        human4.put("title", "Lord");
        assertFalse(schema.isValid(human4));
    }
}