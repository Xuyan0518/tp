package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class JsonAdaptedEntryListTest {

    @Test
    public void testGetEntryList() {
        List<JsonAdaptedEntry> entries = new ArrayList<>();
        entries.add(new JsonAdaptedEntry("Category1", "Description1"));
        entries.add(new JsonAdaptedEntry("Category2", "Description2"));

        JsonAdaptedEntryList entryList = new JsonAdaptedEntryList(entries);
        assertEquals(entries, entryList.getEntryList());
    }

    @Test
    public void testConstructor() {
        List<JsonAdaptedEntry> entries = new ArrayList<>();
        entries.add(new JsonAdaptedEntry("Category1", "Description1"));
        entries.add(new JsonAdaptedEntry("Category2", "Description2"));

        JsonAdaptedEntryList entryList = new JsonAdaptedEntryList(entries);
        assertEquals(entries, entryList.getEntryList());
    }
}
