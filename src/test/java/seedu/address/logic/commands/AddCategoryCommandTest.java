package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AddCategoryCommand.MESSAGE_DUPLICATE_CATEGORY;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Entry;
import seedu.address.model.person.EntryList;
import seedu.address.model.person.Person;

public class AddCategoryCommandTest {
    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCategoryCommand(null, new EntryList()));
    }

    @Test
    public void constructor_nullEntryList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCategoryCommand(Index.fromZeroBased(0), null));
    }

    @Test
    public void execute_validIndexAndEntries_success() throws CommandException {
        Model model = new ModelManager();
        Person person = new Person(new EntryList(), new HashSet<>());
        model.addPerson(person);

        Index index = Index.fromZeroBased(0);
        EntryList entryList = new EntryList();
        entryList.add(new Entry("Category1", "Description1"));

        AddCategoryCommand command = new AddCategoryCommand(index, entryList);
        CommandResult result = command.execute(model);

        assertEquals(String.format(AddCategoryCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(person)),
                result.getFeedbackToUser());

        assertTrue(model.getFilteredPersonList().get(0).getEntry("Category1") != null);
    }

    @Test
    public void execute_duplicateCategory_throwsCommandException() {
        Model model = new ModelManager();
        Person person = new Person(new EntryList(), new HashSet<>());
        person.addEntry(new Entry("Category1", "Description1"));
        model.addPerson(person);

        Index index = Index.fromZeroBased(0);
        EntryList entryList = new EntryList();
        entryList.add(new Entry("Category1", "Description2"));

        AddCategoryCommand command = new AddCategoryCommand(index, entryList);
        assertThrows(CommandException.class, () -> command.execute(model), MESSAGE_DUPLICATE_CATEGORY);
    }
}
