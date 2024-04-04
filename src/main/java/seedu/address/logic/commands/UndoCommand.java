package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Represents an undo command used to revert the last change made to the address book.
 * This command allows users to undo their last action, whether it was a modification
 * to the contacts list, a grouping operation, or any other change that supports undo functionality.
 * The ability to undo depends on the internal tracking of user actions and states within the model.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undo successful.";
    public static final String MESSAGE_FAILURE = "No actions to undo.";
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.canUndo()) {
            model.undo();
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
