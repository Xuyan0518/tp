package seedu.address.logic.commands;

import seedu.address.model.Model;

public class UndoCommand extends Command {
    
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undo successful.";
    public static final String MESSAGE_FAILURE = "No actions to undo.";
    
    @Override
    public CommandResult execute(Model model) {
        if (model.canUndo()) {
            model.undo();
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
