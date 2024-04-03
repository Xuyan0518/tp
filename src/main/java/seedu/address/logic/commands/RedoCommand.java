package seedu.address.logic.commands;

/**
 * Represents a redo command used to reapply the last change made to the address book that was previously undone.
 * This command allows users to redo their last action that was undone, whether it was a modification
 * to the contacts list, a grouping operation, or any other change that supports redo functionality.
 * The ability to redo depends on the internal tracking of user actions and states within the model,
 * and it assumes that an undo operation has occurred before a redo operation can be performed.
 */
public class RedoCommand {
}
