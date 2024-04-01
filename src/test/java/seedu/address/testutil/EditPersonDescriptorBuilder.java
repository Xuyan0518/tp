package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Entry;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor(person);
        descriptor.setTags(person.getTags());
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }
    /**
     * Sets the entry for the specified category in the edit person descriptor.
     * This method allows setting or updating an entry associated with a specific category.
     * If an entry with the given category already exists in the descriptor, its details
     * are updated. Otherwise, the new entry is added.
     * @param entry The entry object containing the details to be set or updated. Cannot be null.
     * @return The instance of this {@code EditPersonDescriptorBuilder} to allow for method chaining.
     */
    public EditPersonDescriptorBuilder addEditorEntry(Entry entry) {
        descriptor.addEntry(entry);
        return this;
    }
    public EditPersonDescriptor build() {
        return descriptor;
    }
}
