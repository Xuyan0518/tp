package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.ModelStub;


public class GroupCommandTest {

    private ModelStub modelStub;
    private GroupCommand groupCommand;

    @BeforeEach
    public void setUp() {
        modelStub = new ModelStub();
    }

    @Test
    public void execute_validCategory_success() {
        groupCommand = new GroupCommand("Age"); // Assuming "Age" is a valid category
        CommandResult result = groupCommand.execute(modelStub);
        assertEquals("All persons have been grouped!", result.getFeedbackToUser());
    }

    @Test
    public void constructor_nullCategory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GroupCommand(null));
    }
}

