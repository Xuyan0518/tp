package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Entry;
import seedu.address.model.person.EntryList;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {
    private EntryList entryList;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        entryList = new EntryList();
        tags = new HashSet<>();
        // Add default values for entries and tags if necessary
    }
    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        entryList = personToCopy.getList();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code EntryList} of the {@code Person} that we are building.
     */
    public PersonBuilder withEntries(Entry... entries) {
        for (Entry entry : entries) {
            this.entryList.add(entry);
        }
        return this;
    }

    /**
     * Adds a new tag to the set of tags of the {@code Person} we are building.
     */
    public PersonBuilder withTag(String... tagName) {
        for (String tag : tagName) {
            this.tags.add(new Tag(tag));
        }
        return this;
    }

    /**
     * Builds and returns the Person.
     */
    public Person build() {
        return new Person(entryList, tags);
    }
}
