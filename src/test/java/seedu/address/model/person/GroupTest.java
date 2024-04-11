package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;


public class GroupTest {
    private final AddressBook addressBook = getTypicalAddressBook();
    private final Group group = new Group();
    @Test
    public void group_nonEmptyListDifferentCategories_success() throws CommandException {
        ArrayList<Person> persons = new ArrayList<>(addressBook.getPersonList());
        group.group(persons, "Clan");
        ArrayList<Person> groupedPersons = group.getGroupList();

        assertEquals(7, groupedPersons.size());
    }
    @Test
    public void group_nonEmptyListMissingCategory_groupedInNoCategory() throws CommandException {
        ArrayList<Person> persons = new ArrayList<>(addressBook.getPersonList());
        group.group(persons, "Clan");
        ArrayList<Person> groupedPersons = group.getGroupList();

        // Assuming there's one person with a category and one without
        assertEquals(7, groupedPersons.size());
    }
    @Test
    public void group_emptyList_throwsCommandException() {
        ArrayList<Person> persons = new ArrayList<>();

        assertThrows(CommandException.class, () -> group.group(persons, "Category"));
    }
    @Test
    public void getGroupList_afterGrouping_returnsCorrectGroups() throws CommandException {
        ArrayList<Person> persons = new ArrayList<>(addressBook.getPersonList());

        group.group(persons, "Clan");
        ArrayList<Person> groupedPersons = group.getGroupList();

        assertNotNull(groupedPersons);
        assertEquals(7, groupedPersons.size());
    }
}
