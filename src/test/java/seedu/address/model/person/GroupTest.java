package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookGroup;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.testutil.PersonBuilder;

public class GroupTest {

    private Group group;
    private final AddressBook addressBook = getTypicalAddressBookGroup();

    @BeforeEach
    public void setUp() {
        group = new Group();
    }

    @Test
    public void group_nonEmptyListDifferentCategories_success() throws CommandException {
        Person person1 = new PersonBuilder().withEntries(new Entry("Clan", "Rain"),
                new Entry("Class", "Warrior"),
                new Entry("Name", "Alice")).build();
        Person person2 = new PersonBuilder().withEntries(new Entry("Clan", "bow"),
                new Entry("Name", "Bob")).build();
        ArrayList<Person> personArrayList = new ArrayList<>(Arrays.asList(person1, person2));
        group.group(personArrayList, "Clan");

        assertEquals(2, group.getGroupList().size());
    }

    @Test
    public void group_nonEmptyListMissingCategory_groupedInNoCategory() throws CommandException {
        Person person1 = new PersonBuilder().withEntries(new Entry("Clan", "Rain"),
                new Entry("Class", "Warrior"),
                new Entry("Name", "Alice")).build();
        Person person2 = new PersonBuilder().withEntries(new Entry("Clan", "bow"),
                new Entry("Name", "Bob")).build();
        ArrayList<Person> personArrayList = new ArrayList<>(Arrays.asList(person1, person2));
        group.group(personArrayList, "Class");
        ArrayList<Person> groupedPersons = group.getGroupList();

        assertEquals(2, groupedPersons.size());
    }

    @Test
    public void group_emptyList_throwsCommandException() {
        ArrayList<Person> persons = new ArrayList<>();
        Group group = new Group();
        assertThrows(CommandException.class, () -> group.group(persons, "Category"));
    }

    @Test
    public void getGroupList_afterGrouping_returnsCorrectGroups() throws CommandException {
        Person person1 = new PersonBuilder().withEntries(new Entry("Clan", "Rain"),
                new Entry("Class", "Warrior"),
                new Entry("Name", "Alice")).build();
        Person person2 = new PersonBuilder().withEntries(new Entry("Clan", "bow"),
                new Entry("Name", "Bob")).build();
        Person person3 = new PersonBuilder().withEntries(new Entry("Clan", "Rain"),
                new Entry("Gender", "Female"),
                new Entry("Name", "Cedric")).build();
        ArrayList<Person> personArrayList = new ArrayList<>(Arrays.asList(person1, person2, person3));

        group.group(personArrayList, "Clan");
        ArrayList<Person> groupedPersons = group.getGroupList();
        assertEquals(2, groupedPersons.size());

        group.group(personArrayList, "Class");
        ArrayList<Person> groupedPersons2 = group.getGroupList();
        assertEquals(2, groupedPersons2.size());

        group.group(personArrayList, "Name");
        ArrayList<Person> groupedPersons3 = group.getGroupList();
        assertEquals(3, groupedPersons3.size());
    }

    @Test
    public void group_nonEmptyListDifferentCategoriesUsingTypicalPerson_success() throws CommandException {
        ArrayList<Person> persons = new ArrayList<>(addressBook.getPersonList());
        group.group(persons, "Clan");
        ArrayList<Person> groupedPersons = group.getGroupList();

        assertEquals(4, groupedPersons.size());
    }
    @Test
    public void group_nonEmptyListMissingCategoryUsingTypicalPerson_groupedInNoCategory() throws CommandException {
        ArrayList<Person> persons = new ArrayList<>(addressBook.getPersonList());
        group.group(persons, "Clan");
        ArrayList<Person> groupedPersons = group.getGroupList();

        // Assuming there's one person with a category and one without
        assertEquals(4, groupedPersons.size());
    }
    @Test
    public void getGroupList_afterGroupingUsingTypicalPerson_returnsCorrectGroups() throws CommandException {
        ArrayList<Person> persons = new ArrayList<>(addressBook.getPersonList());

        group.group(persons, "Clan");
        ArrayList<Person> groupedPersons = group.getGroupList();

        assertNotNull(groupedPersons);
        assertEquals(4, groupedPersons.size());
    }
}
