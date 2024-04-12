package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class JsonAdaptedEntryTest {

    @Test
    public void testGetCategory() {
        String category = "TestCategory";
        String description = "TestDescription";
        JsonAdaptedEntry entry = new JsonAdaptedEntry(category, description);
        assertEquals(category, entry.getCategory());
    }

    @Test
    public void testGetDescription() {
        String category = "TestCategory";
        String description = "TestDescription";
        JsonAdaptedEntry entry = new JsonAdaptedEntry(category, description);
        assertEquals(description, entry.getDescription());
    }

    @Test
    public void testConstructor() {
        String category = "TestCategory";
        String description = "TestDescription";
        JsonAdaptedEntry entry = new JsonAdaptedEntry(category, description);
        assertEquals(category, entry.getCategory());
        assertEquals(description, entry.getDescription());
    }
}
