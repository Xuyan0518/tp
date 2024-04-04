package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

/**
 * Represents a redo command used to reapply the last change made to the address book that was previously undone.
 * This command allows users to redo their last action that was undone, whether it was a modification
 * to the contacts list, a grouping operation, or any other change that supports redo functionality.
 * The ability to redo depends on the internal tracking of user actions and states within the model,
 * and it assumes that an undo operation has occurred before a redo operation can be performed.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redo successful.";
    public static final String MESSAGE_FAILURE = "No undone actions to redo.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.canRedo()) {
            model.redo();
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
