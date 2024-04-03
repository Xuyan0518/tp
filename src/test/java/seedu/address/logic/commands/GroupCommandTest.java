package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class GroupCommandTest {
    private GroupCommand groupCommand;
    @Test
    public void constructor_nullCategory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GroupCommand(null));
    }
}

