package seedu.address.model.person;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Represents a grouping of {@code Person} objects based on a specific category.
 * This class allows for the organization of persons into groups where each group
 * corresponds to a unique value of a specified category.
 */
public class Group {

    private ArrayList<Person> groupList;
    /**
     * Groups a list of {@code Person} objects based on the specified category.
     * Each group is represented by a {@code Person} object where the "Group Name"
     * entry is set to the category value. If the category is not present in a person,
     * they are added to a default group named "No group".
     *
     * @param personArrayList The list of {@code Person} objects to be grouped.
     * @param category The category used to group the {@code Person} objects.
     */
    public void group(ArrayList<Person> personArrayList, String category) {
        if (personArrayList.isEmpty()) {
            return;
        }

        ArrayList<Person> groupedPerson = new ArrayList<>();
        String currCat = null;
        Person currentGroup = null;
        int index = 1;

        for (Person person : personArrayList) {
            String personCat = person.getEntry(category) != null ? person.getEntry(category).getDescription() : null;

            if (personCat == null) {
                if (currentGroup == null) {
                    currentGroup = new Person(new Entry("Group Name", "No group"), new HashSet<>());
                }
                currentGroup.addEntry(new Entry(String.valueOf(index++), person.getEntry("Name").getDescription()));
            } else {
                if (currCat == null || !personCat.equals(currCat)) {
                    if (currentGroup != null) {
                        groupedPerson.add(currentGroup);
                    }
                    currCat = personCat;
                    currentGroup = new Person(new Entry("Group Name", currCat), new HashSet<>());
                    index = 1;
                }
                currentGroup.addEntry(new Entry(String.valueOf(index++), person.getEntry("Name").getDescription()));
            }
        }

        if (currentGroup != null) {
            groupedPerson.add(currentGroup);
        }

        groupList = groupedPerson;
    }
    public ArrayList<Person> getGroupList() {
        return groupList;
    }
}

