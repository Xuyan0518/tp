package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;

import seedu.address.model.Model;

/**
 * creates a groupcommand
 */
public class GroupCommand extends Command {
    public static final String COMMAND_WORD = "group";
    public static final String TOO_MANY_CATEGORIES = "Please specify only 1 category";
    public static final String NO_INPUT = "Please specify a category";

    /**
     * Usage message for the group command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Groups contacts in the address book. "
            + "Parameters: "
            + PREFIX_CATEGORY + "CATEGORY\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CATEGORY + "CLAN ";

    private String category;

    /**
     * Constructs a GroupCommand with the specified category.
     *
     * @param category The category by which contacts will be grouped. Must not be null.
     * @throws NullPointerException if the specified category is null.
     */
    public GroupCommand(String category) {
        requireNonNull(category);
        this.category = category;
    }

    /**
     * executes the model to group list
     * @param model {@code Model} which the command should operate on.
     * @return success result
     */
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.groupPerson(category);
        return new CommandResult("All persons have been grouped!");
    }
}
