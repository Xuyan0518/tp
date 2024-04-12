package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Entry;
import seedu.address.model.person.EntryList;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {
    //testing pull request
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_CATEGORY + "CATEGORY] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CATEGORY + "Clan "
            + PREFIX_DESCRIPTION + "Kingdom";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_CATEGORY_DOESNT_EXIST = "Category doesnt exist!";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_EMPTY_DESCRIPTION = "Invalid edit, please provide a description!";
    public static final String ENTRY_NOT_ADDED = "Both fields to add must be provided.";
    public static final String DIFFERENT_NUMBER_CATEGORIES_DESCRIPTIONS =
            "Number of specified categories and descriptions must be the same.";
    public static final String MESSAGE_DUPLICATE_CATEGORY = "No duplicate categories allowed.";
    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person originalPerson = personToEdit.deepCopy();
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);
        logger.info("Edited Person: " + editedPerson);
        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        model.savePersonState(editedPerson, originalPerson);
        ModelManager.getActionTracker().push(false);
        model.clearUndoHistory();
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }
    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor
            editPersonDescriptor) throws CommandException {
        assert personToEdit != null;
        assert editPersonDescriptor != null;
        EntryList entryList = editPersonDescriptor.getEntryList();
        for (int i = 0; i < entryList.size(); i++) {
            Entry toAdd = entryList.get(i);
            Entry test = personToEdit.getEntry(toAdd.getCategory().toLowerCase());
            if (test == null) {
                throw new CommandException(MESSAGE_CATEGORY_DOESNT_EXIST);
            } else {
                Entry entryToAdd = new Entry(test.getCategory(), toAdd.getDescription());
                personToEdit.deleteEntry(toAdd.getCategory());
                personToEdit.addEntry(entryToAdd);
            }
        }
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        personToEdit.setTags(updatedTags);
        return personToEdit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Set<Tag> tags;
        private EntryList entryList = new EntryList();
        private String category;
        private String description;
        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor() {}
        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            assert toCopy != null;
            this.entryList = toCopy.entryList;
            this.tags = toCopy.tags;
            this.category = toCopy.getCategory();
            this.description = toCopy.getDescription();
        }
        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(Person toCopy) {
            assert toCopy != null;
            this.entryList = toCopy.getList();
            this.tags = toCopy.getTags();
        }
        // Getter and setter for category and description
        public String getCategory() {
            return category;
        }
        public void setCategory(String category) {
            this.category = category;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyTagEdited() {
            return CollectionUtil.isAnyNonNull(tags);
        }
        /**
         * Sets an entry in the entry list based on the specified category.
         * If an entry with the given category exists, its description is updated.
         * Otherwise, the new entry is added to the list.
         *
         * @param category the category of the entry to be set. This is used to find
         *                 an existing entry in the list.
         * @param entry the new entry to be added or used for updating the description of the
         *              existing entry. It should not be {@code null}.
         */
        public void set(String category, Entry entry) {
            Entry e = entryList.get(category);
            if (e == null) {
                entryList.add(entry);
            } else {
                e.setDescription(entry.getDescription());
            }
        }
        public void addEntry(Entry entry) {
            entryList.add(entry);
        }
        public void setEntryList(EntryList entryList) {
            this.entryList = entryList;
        }
        public EntryList getEntryList() {
            return entryList;
        }
        /**
         * Gets category from the entry
         * @param category of the entry list
         * @return the category
         */
        public Optional<Entry> get(String category) {
            return Optional.ofNullable(entryList.get(category));
        }
        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            EditPersonDescriptor otherEditPersonDescriptor = (EditPersonDescriptor) other;
            return Objects.equals(entryList, otherEditPersonDescriptor.getEntryList())
                    && Objects.equals(tags, otherEditPersonDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("Category", category)
                    .add("Description", description)
                    .add("tags", tags)
                    .toString();
        }
    }
}
