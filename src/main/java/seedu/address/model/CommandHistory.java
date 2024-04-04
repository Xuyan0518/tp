package seedu.address.model;

import java.util.Deque;
import java.util.LinkedList;
/**
 * Represents a history of commands executed on the address book, allowing for undo operations.
 * This class maintains a stack-like structure of address book states, where each state represents
 * the address book at a certain point in time. This enables the application to revert to previous
 * states in response to an undo command, effectively undoing recent changes.
 */
public class CommandHistory {
    private Deque<ReadOnlyAddressBook> history = new LinkedList<>();

    /**
     * Pushing the current state of the address book into the command history.
     * @param currentState represents the current address book.
     */
    public void push(ReadOnlyAddressBook currentState) {
        history.push(currentState);
    }

    /**
     * Retrieve the most recent address book state.
     * @return
     */
    public ReadOnlyAddressBook pop() {
        return history.pop();
    }

    /**
     * Check for the presence of command history.
     * @return
     */
    public boolean isEmpty() {
        return history.isEmpty();
    }

    /**
     * Finds the number of copies of histories
     */
    public int size() {
        return history.size();
    }
}
