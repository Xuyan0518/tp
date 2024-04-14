package seedu.address.model.person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Represents a grouping of {@code Person} objects based on a specific category.
 * This class allows for the organization of persons into groups where each group
 * corresponds to a unique value of a specified category.
 */
public class Group {
    private static final String MESSAGE_EMPTY_ADDRESS_BOOK = "Address book is empty!";
    private static final String NO_GROUP_TEXT = "No group";
    private ArrayList<Person> groupList;
    private final Logger logger = LogsCenter.getLogger(getClass());
    /**
     * Groups a list of {@code Person} objects based on the specified category.
     * Each group is represented by a {@code Person} object where the "Group Name"
     * entry is set to the category value. If the category is not present in a person,
     * they are added to a default group named "No group".
     * @param personArrayList The list of {@code Person} objects to be grouped.
     * @param category The category used to group the {@code Person} objects.
     */
    public void group(ArrayList<Person> personArrayList, String category) throws CommandException {
        assert personArrayList != null;
        if (personArrayList.isEmpty()) {
            logger.warning("The addressbook is empty. Check json file.");
            throw new CommandException(MESSAGE_EMPTY_ADDRESS_BOOK);
        }
        Map<String, Person> groupMap = new HashMap<>();
        Person noCategoryGroup = new Person(new Entry("Group Name", NO_GROUP_TEXT), new HashSet<>());
        boolean noCategoryGroupAdded = false;

        for (Person person : personArrayList) {
            String personCat = person.getEntry(category) != null ? person.getEntry(category).getDescription() : null;

            if (personCat == null) {
                noCategoryGroup.addEntry(new Entry("No category", person.getEntry("Name").getDescription()));
                noCategoryGroupAdded = true;
            } else {
                Person group = groupMap.get(personCat);
                if (group == null) {
                    group = new Person(new Entry("Group Name", personCat), new HashSet<>());
                    groupMap.put(personCat, group);
                }
                group.addEntry(new Entry("Name", person.getEntry("Name").getDescription()));
            }
        }
        ArrayList<Person> groupedPerson = new ArrayList<>(groupMap.values());

        if (noCategoryGroupAdded) {
            groupedPerson.add(noCategoryGroup);
        }
        groupList = groupedPerson;
        logger.info("Grouped people: " + groupList);
    }
    public ArrayList<Person> getGroupList() {
        return groupList;
    }
}
