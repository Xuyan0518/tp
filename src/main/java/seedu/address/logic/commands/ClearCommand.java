package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import javafx.scene.control.ButtonType;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.ui.CustomConfirmationDialog;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    public static final String MESSAGE_CANCEL = "Clear command cancelled!";
    public static final String MESSAGE_CONFIRMATION =
            "Are you sure you want to clear the address book? This action cannot be undone.";

    /**
     * Executes the clear command.
     *
     * @param model The model containing the address book.
     * @return The result of the command execution.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearUndoHistory();
        model.clearAllCommandHistories();
        ModelManager.getActionTracker().clear();
        ModelManager.getUndoActionTracker().clear();

        CustomConfirmationDialog confirmationDialog = new CustomConfirmationDialog("Clear Command",
                MESSAGE_CONFIRMATION);
        Optional<ButtonType> result = confirmationDialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            model.setAddressBook(new AddressBook());
            model.setGroupAddressBook(new AddressBook());
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_CANCEL);
        }
    }
}

