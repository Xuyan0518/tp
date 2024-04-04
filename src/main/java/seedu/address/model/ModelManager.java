package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Group;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private static Stack<Boolean> actionTracker = new Stack<>();
    private final AddressBook addressBook;

    private final AddressBook groupAddressBook;
    private final UserPrefs userPrefs;
    private FilteredList<Person> filteredPersons;
    private CommandHistory commandHistory = new CommandHistory();
    private UndoHistory undoHistory = new UndoHistory();
    private boolean lastUndoActionWasGroup = false;
    private FilteredList<Person> filteredGroupPerson;

    private CommandHistory groupCommandHistory = new CommandHistory();
    private UndoHistory groupUndoHistory = new UndoHistory();

    private Group group = new Group();


    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        //creates an empty addressbook for group.
        this.groupAddressBook = new AddressBook();
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredGroupPerson = new FilteredList<>(this.groupAddressBook.getPersonList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    public static Stack<Boolean> getActionTracker() {
        return ModelManager.actionTracker;
    }
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }
    @Override
    public void groupPerson(String category) {
        ArrayList<Person> persons = new ArrayList<>(addressBook.getPersonList());
        for (Person person: persons) {
            person.toCompare(category);
        }
        Collections.sort(persons);
        group.group(persons, category);
        saveGroupAddressBookState();
        groupAddressBook.setPersons(group.getGroupList());
        ModelManager.getActionTracker().push(true);
        filteredGroupPerson = new FilteredList<>(groupAddressBook.getPersonList());
    }


    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public void setGroupAddressBook(ReadOnlyAddressBook addressBook) {
        this.groupAddressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }
    @Override
    public void undo() {
        if (actionTracker.isEmpty()) {
            System.out.println("Nothing to undo.");
            return;
        }
        // Peek at the last action to decide whether it's a group or non-group action.
        boolean lastActionWasGroup = actionTracker.peek();
        if (lastActionWasGroup) {
            // If the last action was a group action and there's something to undo.
            if (!groupCommandHistory.isEmpty()) {
                undoGrouping();
                // After undoing, pop the action type since it's been handled.
                actionTracker.pop();
            }
        } else {
            // If the last action was a non-group action and there's something to undo.
            if (!commandHistory.isEmpty()) {
                setAddressBook(commandHistory.pop());
                // After undoing, pop the action type since it's been handled.
                actionTracker.pop();
            }
        }
        System.out.println("Non-group command history: " + commandHistory.size());
        System.out.print("Group command history: " + groupCommandHistory.size() + System.lineSeparator());
        System.out.println("Action tracker: " + ModelManager.getActionTracker().toString());
    }
    @Override
    public void redo() {
        System.out.println("Non-group undo history: " + undoHistory.size());
        System.out.print("Group undo history: " + groupUndoHistory.size() + System.lineSeparator());
        if (lastUndoActionWasGroup && !groupUndoHistory.isEmpty() && groupUndoHistory.size() != 1) {
            redoGrouping();
            lastUndoActionWasGroup = true;
        } else if (lastUndoActionWasGroup && groupUndoHistory.size() == 1) {
            redoGrouping();
            lastUndoActionWasGroup = false;
        } else if (!undoHistory.isEmpty()) {
            saveAddressBookState();
            setAddressBook(undoHistory.pop());
            //lastActionWasGroup = false;
        }
    }
    private void undoGrouping() {
        //saveUndoneGroupAddressBookState();
        setGroupAddressBook(groupCommandHistory.pop());
    }
    private void redoGrouping() {
        saveGroupAddressBookState();
        setGroupAddressBook(groupUndoHistory.pop());
        //lastActionWasGroup = true;
    }
    @Override
    public boolean canUndo() {
        return (!commandHistory.isEmpty() || !groupCommandHistory.isEmpty()) && !actionTracker.isEmpty();
    }
    @Override
    public boolean canRedo() {
        return !undoHistory.isEmpty();
    }
    @Override
    public boolean canRedoGrouping() {
        return lastUndoActionWasGroup && !groupUndoHistory.isEmpty();
    }
    @Override
    public void saveAddressBookState() {
        commandHistory.push(new AddressBook(addressBook));
    }
    @Override
    public void saveUndoneAddressBookState() {
        undoHistory.push(new AddressBook(addressBook));
        lastUndoActionWasGroup = false;
    }
    @Override
    public void saveGroupAddressBookState() {
        groupCommandHistory.push(new AddressBook(groupAddressBook));
    }
    @Override
    public void saveUndoneGroupAddressBookState() {
        groupUndoHistory.push(new AddressBook(groupAddressBook));
        lastUndoActionWasGroup = true;
    }
    @Override
    public void savePersonState(Person before, Person after) {
        AddressBook withEditedPerson = new AddressBook(addressBook);
        withEditedPerson.replacePerson(before, after);
        commandHistory.push(withEditedPerson);
    }
    @Override
    public void replacePerson(Person target, Person replacing) {
        this.addressBook.replacePerson(target, replacing);
    }
    @Override
    public void clearUndoHistory() {
        this.undoHistory.clear();
    }
    @Override
    public void clearAllCommandHistories() {
        commandHistory.clear();
        groupCommandHistory.clear();
    }
    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public ObservableList<Person> getFilteredGroupPersonList() {
        return filteredGroupPerson;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

}
