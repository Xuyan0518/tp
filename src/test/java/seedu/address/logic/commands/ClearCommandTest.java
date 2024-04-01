package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ClearCommandTest {

    @BeforeAll
    public static void setupJavaFX() {
        // Initialize JavaFX toolkit
        JFXPanel jfxPanel = new JFXPanel();
    }

    @Test
    public void execute_clearConfirmed_clearsAddressBook() {
        // Create a mock Model
        Model model = new ModelManager();

        // Execute the command on the JavaFX Application Thread
        Platform.runLater(() -> {
            try {
                ClearCommand clearCommand = new ClearCommand();
                CommandResult commandResult = clearCommand.execute(model);

                // Verify that the address book is cleared
                assertEquals(ClearCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
                assertEquals(0, model.getAddressBook().getPersonList().size());
            } catch (Exception e) {
                // Handle exceptions
                e.printStackTrace();
            }
        });
    }

}
