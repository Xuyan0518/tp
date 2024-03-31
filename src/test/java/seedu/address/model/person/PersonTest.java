package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class PersonTest {

    @Test
    public void constructor_validNameAndTags_success() {
        Entry name = new Entry("Name", "John Doe");
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("Friend"));
        Person person = new Person(name, tags);
        assertEquals(name, person.getEntry("Name"));
        assertEquals(tags, person.getTags());
    }

    @Test
    public void isSamePerson_samePerson_returnsTrue() {
        Person person = new Person(new Entry("Name", "John Doe"), Collections.emptySet());
        assertTrue(person.isSamePerson(person));
    }

    @Test
    public void isSamePerson_differentPerson_returnsFalse() {
        Person person1 = new Person(new Entry("Name", "John Doe"), Collections.emptySet());
        Person person2 = new Person(new Entry("Name", "Jane Doe"), Collections.emptySet());
        assertFalse(person1.isSamePerson(person2));
    }

    @Test
    public void equals_samePerson_returnsTrue() {
        Person person = new Person(new Entry("Name", "John Doe"), Collections.emptySet());
        assertTrue(person.equals(person));
    }

    @Test
    public void equals_differentPerson_returnsFalse() {
        Person person1 = new Person(new Entry("Name", "John Doe"), Collections.emptySet());
        Person person2 = new Person(new Entry("Name", "Jane Doe"), Collections.emptySet());
        assertFalse(person1.equals(person2));
    }
}
