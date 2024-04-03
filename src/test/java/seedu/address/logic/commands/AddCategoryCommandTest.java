package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.EntryList;

public class AddCategoryCommandTest {
    private AddCategoryCommand addCategoryCommand;
    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCategoryCommand(null, new EntryList()));
    }

    @Test
    public void constructor_nullEntryList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCategoryCommand(Index.fromZeroBased(0), null));
    }
}
