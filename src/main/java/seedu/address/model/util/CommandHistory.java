package seedu.address.model.util;

import seedu.address.model.ReadOnlyAddressBook;

import java.util.Deque;
import java.util.LinkedList;

public class CommandHistory {
    private Deque<ReadOnlyAddressBook> history = new LinkedList<>();
    
    public void push(ReadOnlyAddressBook currentState) {
        history.push(currentState);
    }
    
    public ReadOnlyAddressBook pop() {
        return history.pop();
    }
    
    public boolean isEmpty() {
        return history.isEmpty();
    }
}
