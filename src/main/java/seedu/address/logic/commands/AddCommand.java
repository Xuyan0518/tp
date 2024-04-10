package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Entry;
import seedu.address.model.person.EntryList;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    /**
     * The command word for adding a person.
     */
    public static final String COMMAND_WORD = "add";

    /**
     * Usage message for the add command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_TAG + "TAG]..."
            + "[" + PREFIX_CATEGORY + "CATEGORY]... "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_TAG + "owesMoney "
            + PREFIX_CATEGORY + "clan "
            + PREFIX_DESCRIPTION + "rainbow";

    /**
     * Message indicating successful addition of a person.
     */
    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_NAME = "This person already exists in the address book!";
    private final Person toAdd;
    private final EntryList entryList;

    /**
     * Creates an AddCommand to add the specified person.
     *
     * @param person    The person to be added.
     * @param entryList The list of entries associated with the person.
     */
    public AddCommand(Person person, EntryList entryList) {
        requireNonNull(person);
        this.toAdd = person;
        this.entryList = entryList;
    }

    /**
     * Executes the add command by adding the person to the model.
     *
     * @param model The model in which the person is to be added.
     * @return CommandResult indicating the outcome of the command execution.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.saveAddressBookState();
        ModelManager.getActionTracker().push(false);
        model.clearUndoHistory();
        assert toAdd != null : "Person to add cannot be null";

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_NAME);
        }
        if (entryList == null) {
            model.addPerson(toAdd);
        } else {
            for (int i = 0; i < entryList.size(); i++) {
                Entry entry = entryList.get(i);
                toAdd.addEntry(entry);
            }
            model.addPerson(toAdd);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    /**
     * Checks equality with another object.
     *
     * @param other The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    /**
     * Generates a string representation of the AddCommand.
     *
     * @return String representation of the AddCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
