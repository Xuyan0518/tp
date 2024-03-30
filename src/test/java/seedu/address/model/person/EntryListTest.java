package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class EntryListTest {

    @Test
    public void add_validEntry_success() {
        EntryList entryList = new EntryList();
        Entry entry = new Entry("Category", "Description");
        entryList.add(entry);
        assertEquals(1, entryList.size());
    }

    @Test
    public void get_validIndex_returnsEntry() {
        EntryList entryList = new EntryList();
        Entry entry = new Entry("Category", "Description");
        entryList.add(entry);
        assertEquals(entry, entryList.get(0));
    }

    @Test
    public void get_invalidIndex_returnsNull() {
        EntryList entryList = new EntryList();
        assertNull(entryList.get(0));
    }

    @Test
    public void get_validCategory_returnsEntry() {
        EntryList entryList = new EntryList();
        Entry entry = new Entry("Category", "Description");
        entryList.add(entry);
        assertEquals(entry, entryList.get("Category"));
    }

    @Test
    public void get_invalidCategory_returnsNull() {
        EntryList entryList = new EntryList();
        assertNull(entryList.get("NonexistentCategory"));
    }

    @Test
    public void size_emptyList_returnsZero() {
        EntryList entryList = new EntryList();
        assertEquals(0, entryList.size());
    }

    @Test
    public void delete_validCategory_success() {
        EntryList entryList = new EntryList();
        Entry entry = new Entry("Category", "Description");
        entryList.add(entry);
        entryList.delete("Category");
        assertEquals(0, entryList.size());
    }

    @Test
    public void delete_invalidCategory_noChange() {
        EntryList entryList = new EntryList();
        Entry entry = new Entry("Category", "Description");
        entryList.add(entry);
        entryList.delete("NonexistentCategory");
        assertEquals(1, entryList.size());
    }

    @Test
    public void sort_validList_sorted() {
        EntryList entryList = new EntryList();
        entryList.add(new Entry("Z", "Description Z"));
        entryList.add(new Entry("A", "Description A"));
        entryList.sort();
        assertEquals("A", entryList.get(0).getCategory());
        assertEquals("Z", entryList.get(1).getCategory());
    }

    @Test
    public void toString_validList_returnsStringRepresentation() {
        EntryList entryList = new EntryList();
        entryList.add(new Entry("Category1", "Description1"));
        entryList.add(new Entry("Category2", "Description2"));
        assertEquals("seedu.address.model.person.EntryList{Category=Category1, "
                + "Description=Description1, Category=Category2, Description=Description2}", entryList.toString());
    }
}
