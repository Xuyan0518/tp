package seedu.address.model.person;

import java.util.ArrayList;
import java.util.HashSet;

public class Group {

    private ArrayList<Person> groupList;

    public void group(ArrayList<Person> personArrayList, String category) {
        ArrayList<Person> groupedPerson = new ArrayList<>();
        String currCat = personArrayList.get(0).getEntry(category).getDescription();
        Person init = new Person(new Entry("Group Name: ", currCat), new HashSet<>());
        Person noCat = new Person(new Entry("Group name: ", "No group"), new HashSet<>());
        for (Person person : personArrayList) {
            if (person.getEntry(category) == null) {
                noCat.addEntry(new Entry("Name", person.getEntry("Name").getDescription()));
            }
            if (person.getEntry(category).getDescription().equals(currCat)) {
                init.addEntry(new Entry("Name", person.getEntry("Name").getDescription()));
            } else {
                groupedPerson.add(init);
                currCat = person.getEntry(category).getDescription();
                init = new Person(new Entry("Group Name: ", currCat), new HashSet<>());
                init.addEntry(new Entry("Name", person.getEntry("Name").getDescription()));
            }
        }
        groupedPerson.add(noCat);
        groupList = groupedPerson;
    }
}

