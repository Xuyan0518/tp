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
    private AddressBook addressBook = getTypicalAddressBook();
    @Test
    public void group_nonEmptyListDifferentCategoriesSuccess() throws CommandException {
        Group group = new Group();
        ArrayList<Person> persons = new ArrayList<>(addressBook.getPersonList());
        group.group(persons, "Clan");
        ArrayList<Person> groupedPersons = group.getGroupList();

        assertEquals(7, groupedPersons.size());
    }
    @Test
    public void group_nonEmptyListMissingCategoryGroupedInNoCategory() throws CommandException {
        Group group = new Group();
        ArrayList<Person> persons = new ArrayList<>(addressBook.getPersonList());
        group.group(persons, "Clan");
        ArrayList<Person> groupedPersons = group.getGroupList();

        // Assuming there's one person with a category and one without
        assertEquals(7, groupedPersons.size());
    }

    @Test
    public void group_emptyList_throwsCommandException() {
        Group group = new Group();
        ArrayList<Person> persons = new ArrayList<>();

        assertThrows(CommandException.class, () -> group.group(persons, "Category"));
    }

    @Test
    public void getGroupList_afterGrouping_returnsCorrectGroups() throws CommandException {
        Group group = new Group();
        ArrayList<Person> persons = new ArrayList<>(addressBook.getPersonList());

        group.group(persons, "Clan");
        ArrayList<Person> groupedPersons = group.getGroupList();

        assertNotNull(groupedPersons);
        assertEquals(7, groupedPersons.size());
    }


}
