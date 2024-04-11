package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Entry;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


public class ListCommandTest {

    private ListCommand listCommand;
    private Model model;

    @BeforeEach
    public void setUp() {
        listCommand = new ListCommand();
        model = new ModelManager();
    }

    @Test
    public void execute_listIsSuccessful() {
        // Given
        List<Person> persons = createPersons();
        for (int i = 0; i < persons.size(); i++) {
            model.addPerson(persons.get(i));
        }

        CommandResult result = listCommand.execute(model);

        // Then
        assertNotNull(result);
        assertEquals("Listed all persons", result.getFeedbackToUser());
    }

    private List<Person> createPersons() {
        Person person1 = new PersonBuilder().withEntries(new Entry("Clan", "Rain"),
                new Entry("Class", "Warrior"),
                new Entry("Name", "Alice")).build();
        Person person2 = new PersonBuilder().withEntries(new Entry("Clan", "bow"),
                new Entry("Name", "Bob")).build();
        Person person3 = new PersonBuilder().withEntries(new Entry("Clan", "Rain"),
                new Entry("Gender", "Female"),
                new Entry("Name", "Cedric")).build();
        ArrayList<Person> persons = new ArrayList<>(Arrays.asList(person1, person2, person3));
        return persons;
    }
}

