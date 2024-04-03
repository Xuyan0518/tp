package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SIXTH_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Entry;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class DeleteCategoryCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validOneCategoryDeleteCategory_success() {
        Index validIndex = INDEX_SIXTH_PERSON;
        List<String> validCategory = List.of("Clan");
        DeleteCategoryCommand deleteCategoryCommand = new DeleteCategoryCommand(validIndex, validCategory);
        Person expectedPerson = new PersonBuilder().withEntries(
                new Entry("Name", "Fiona"),
                new Entry("Class", "Sailor"),
                new Entry("Game", "Adventure")
            ).withTag("Explorer", "Seafarer").build();
        String expectedMessage = String.format(DeleteCategoryCommand.MESSAGE_DELETE_CATEGORY_SUCCESS,
                Messages.format(expectedPerson));
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        try {
            CommandResult result = deleteCategoryCommand.execute(model);
            assertEquals(expectedCommandResult, result);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    @Test
    public void execute_validManyCategoriesDeleteCategory_failure() {
        Index validIndex = INDEX_FOURTH_PERSON;
        List<String> validCategory = List.of("Clan", "Class");
        DeleteCategoryCommand deleteCategoryCommand = new DeleteCategoryCommand(validIndex, validCategory);
        Person expectedPerson = new PersonBuilder().withEntries(
                new Entry("Name", "Daniel"),
                new Entry("Game", "Stealth")
        ).withTag("Sneaky", "Assassin").build();
        String expectedMessage = String.format(DeleteCategoryCommand.MESSAGE_DELETE_CATEGORY_SUCCESS,
                Messages.format(expectedPerson));
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        try {
            CommandResult result = deleteCategoryCommand.execute(model);
            assertEquals(expectedCommandResult, result);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }
}
