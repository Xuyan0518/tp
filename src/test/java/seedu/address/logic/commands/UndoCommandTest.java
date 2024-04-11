package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Entry;
import seedu.address.model.person.EntryList;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
/**
 * Represents the test class for the {@code UndoCommand} in the address book application.
 * This class tests the ability of the {@code UndoCommand} to revert the state of the address book
 * to its previous state.
 */
public class UndoCommandTest {
    private Model model;
    private Model expectedModel;
    /**
     * Sets up the model and expectedModel with a typical address book before each test.
     * This ensures a consistent starting state for tests.
     */
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }
    /**
     * Tests the undo functionality after executing an add command.
     * It verifies that the state of the address book is correctly reverted to its
     * previous state after adding a new entry and then undoing the add operation.
     *
     * @throws CommandException if an error occurs during command execution.
     */
    @Test
    public void execute_undoAfterAddCommand_success() throws CommandException {
        EntryList list = new EntryList();
        list.add(new Entry("Category", "Description"));
        Person toAdd = new PersonBuilder().withEntries(list.get(0)).build();
        AddCommand addCommand = new AddCommand(toAdd, list);
        CommandResult resultAfterAdd = addCommand.execute(model);
        CommandResult resultAfterUndo = new UndoCommand().execute(model);
        assertEquals(model.getAddressBook(), expectedModel.getAddressBook());
    }
    /**
     * Tests the undo functionality after executing a delete command.
     * It verifies that the state of the address book is correctly reverted to its
     * previous state after deleting an entry and then undoing the delete operation.
     *
     * @throws CommandException if an error occurs during command execution.
     */
    @Test
    public void execute_undoAfterDeleteCommand_success() throws CommandException {
        Index index = Index.fromZeroBased(0);
        DeleteCommand deleteCommand = new DeleteCommand(index);
        CommandResult resultAfterDelete = deleteCommand.execute(model);
        CommandResult resultAfterUndo = new UndoCommand().execute(model);
        assertEquals(model, expectedModel);
    }
}
