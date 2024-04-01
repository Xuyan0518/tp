package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.EntryList;
import seedu.address.model.person.Person;
import seedu.address.testutil.ModelStub;

public class AddCategoryCommandTest {
    private AddCategoryCommand addCategoryCommand;
    private ModelStub modelStub;

    @BeforeEach
    public void setUp() {
        Index index = Index.fromZeroBased(0);
        EntryList entryList = new EntryList();
        addCategoryCommand = new AddCategoryCommand(index, entryList);
        modelStub = new ModelStub();
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCategoryCommand(null, new EntryList()));
    }

    @Test
    public void constructor_nullEntryList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCategoryCommand(Index.fromZeroBased(0), null));
    }

    @Test
    public void execute_validIndex_addsCategoryToPerson() {
        assertThrows(CommandException.class, () -> addCategoryCommand.execute(modelStub));
    }

    // Add more tests here as neede

    public class ModelStubAcceptingPersonAdded extends ModelStub {
        private final ObservableList<Person> personsAdded = FXCollections.observableArrayList();

        @Override
        public void addPerson(Person person) {
            personsAdded.add(person);
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return personsAdded;
        }
    }
}
