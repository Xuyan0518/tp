package seedu.address.testutil;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;

/**
 * stub class for testing model
 */
public class ModelStub implements Model {
    private final ObservableList<Person> persons = FXCollections.observableArrayList();

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        // No implementation required for testing
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return null; // No implementation required for testing
    }

    @Override
    public GuiSettings getGuiSettings() {
        return null; // No implementation required for testing
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        // No implementation required for testing
    }

    @Override
    public Path getAddressBookFilePath() {
        return null; // No implementation required for testing
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        // No implementation required for testing
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        // No implementation required for testing
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return null; // No implementation required for testing
    }

    @Override
    public boolean hasPerson(Person person) {
        return persons.contains(person);
    }

    @Override
    public void deletePerson(Person target) {
        persons.remove(target);
    }

    @Override
    public void addPerson(Person person) {
        persons.add(person);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        int index = persons.indexOf(target);
        if (index != -1) {
            persons.set(index, editedPerson);
        }
    }

    public void groupPerson(String string) {}

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return persons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        // No implementation required for testing
    }
}
