package seedu.address.model;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Represents a history of commands that have been undone in the address book, allowing for redo operations.
 * This class maintains a stack-like structure where each element represents the address book state
 * just after a command was undone. This enables the application to reapply changes that were undone.
 */
public class UndoHistory {
    private Deque<ReadOnlyAddressBook> undoHistory = new LinkedList<>();

    /**
     * Pushing the state of the address book just undone into the undo history.
     * @param stateJustUndone represents the address book state just undone.
     */
    public void push(ReadOnlyAddressBook stateJustUndone) {
        undoHistory.push(stateJustUndone);
    }

    /**
     * Retrieve the most recent address book state.
     * @return The last undone address book state for redo operations.
     */
    public ReadOnlyAddressBook pop() {
        return undoHistory.pop();
    }

    /**
     * Check for the presence of undo history.
     * @return true if there are states to redo.
     */
    public boolean isEmpty() {
        return undoHistory.isEmpty();
    }

    /**
     * Finds the number of copies of undo histories
     */
    public int size() {
        return undoHistory.size();
    }

    /**
     * Clears all states from the undo history.
     * This method is used to reset the undo history when input new command except undo.
     */
    public void clear() {
        undoHistory.clear();
    }
}
