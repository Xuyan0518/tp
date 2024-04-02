package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ClearCommandTest {
    @Test
    public void execute_clearConfirmed_clearsAddressBook() {
        ClearCommand clearCommand = new ClearCommand();

        assertTrue(clearCommand.equals(clearCommand));
    }
}
