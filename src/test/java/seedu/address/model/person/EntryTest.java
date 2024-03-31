package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EntryTest {

    @Test
    public void constructor_validEntry_success() {
        Entry entry = new Entry("Category", "Description");
        assertEquals("Category", entry.getCategory());
        assertEquals("Description", entry.getDescription());
    }

    @Test
    public void isValid_validEntry_returnsTrue() {
        assertTrue(Entry.isValid("Category", "Description"));
    }

    @Test
    public void isValid_invalidDescription_returnsFalse() {
        assertFalse(Entry.isValid("Category", ""));
    }

    @Test
    public void isValid_invalidCategory_returnsFalse() {
        assertFalse(Entry.isValid("", "Description"));
    }

    @Test
    public void toString_validEntry_returnsStringRepresentation() {
        Entry entry = new Entry("Category", "Description");
        assertEquals("Category: Description", entry.toString());
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        Entry entry = new Entry("Category", "Description");
        assertTrue(entry.equals(entry));
    }

    @Test
    public void equals_differentClass_returnsFalse() {
        Entry entry = new Entry("Category", "Description");
        assertFalse(entry.equals("Not an Entry object"));
    }

    @Test
    public void equals_equalAttributes_returnsFalse() {
        Entry entry1 = new Entry("Category", "Description");
        Entry entry2 = new Entry("Category", "Description");
        assertFalse(entry1.equals(entry2));
    }

    @Test
    public void equals_differentCategory_returnsFalse() {
        Entry entry1 = new Entry("Category1", "Description");
        Entry entry2 = new Entry("Category2", "Description");
        assertFalse(entry1.equals(entry2));
    }

    @Test
    public void equals_differentDescription_returnsFalse() {
        Entry entry1 = new Entry("Category", "Description1");
        Entry entry2 = new Entry("Category", "Description2");
        assertFalse(entry1.equals(entry2));
    }

    @Test
    public void hashCode_sameObject_equalHashCode() {
        Entry entry = new Entry("Category", "Description");
        assertEquals(entry.hashCode(), entry.hashCode());
    }
}
