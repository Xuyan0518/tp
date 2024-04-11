package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class GroupCommandTest {
    private GroupCommand groupCommand;
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void constructor_nullCategory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GroupCommand(null));
    }

    @Test
    public void constructor_validCategory_createsGroupCommand() {
        String category = "Friends";
        groupCommand = new GroupCommand(category);
        assertNotNull(groupCommand);
        // You can also add checks to ensure the category is correctly set, if such getters are available
    }

    @Test
    public void execute_validCategory_groupsSuccessfully() throws CommandException {
        String category = "Friends";
        groupCommand = new GroupCommand(category);

        // Execute the command
        CommandResult commandResult = groupCommand.execute(model);

        // Verify the outcome
        assertNotNull(commandResult);
        assertEquals("All person in the address book are grouped.", commandResult.getFeedbackToUser());
        // Additional assertions can be made to verify the state of the model if the grouping effect is observable
    }


}

