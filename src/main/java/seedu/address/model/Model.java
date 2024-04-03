package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();
    /**
     * Groups persons in the address book based on a specified category. This method allows for organizing
     * contacts into groups, such as by last name, city, or any other specified category.
     * @param category The category by which to group persons.
     */
    void groupPerson(String category);

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);
    /**
     * Performs an undo action. This method allows reverting the last change made to the address book data,
     * supporting undo functionality for user actions.
     */
    void undo();
    /**
     * Checks if an undo operation can be performed. This method allows the application to determine
     * if there are actions that can be undone, typically to enable or disable undo functionality in the UI.
     * @return true if an undo operation can be performed, false otherwise.
     */
    boolean canUndo();
    /**
     * Saves the address book state.
     */
    void saveAddressBookState();
    /**
     * Saves the group address book state.
     */
    void saveGroupAddressBookState();
    /**
     * Saves the person's current state by replacing the before with after.
     * @param before person to be replaced.
     * @param after the replacing person.
     */
    void savePersonState(Person before, Person after);
    /**
     * Checks if a group command can be undone.
     * @return
     */
    boolean canUndoGrouping();
    /**
     * Replaces the target person with the replacing person.
     * @param target person to be replaced.
     * @param replacing to replace the target.
     */
    void replacePerson(Person target, Person replacing);
}
