package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_CANNOT_DELETE_NAME;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCategoryCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser class for deleteCategoryCommand
 */
public class DeleteCategoryCommandParser implements Parser<DeleteCategoryCommand> {
    /**
     * Creates the deleteCategoryCommand
     *
     * @param args our arguments
     * @return the command
     * @throws ParseException if no index
     */
    @Override
    public DeleteCategoryCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, DeleteCategoryCommand.MESSAGE_USAGE
            ), pe);
        }

        // Gets each category from the command
        List<String> categories = argMultimap.getAllValues(PREFIX_CATEGORY);
        if (categories.isEmpty()) {
            throw new ParseException(DeleteCategoryCommand.MESSAGE_EMPTY_CATEGORY);
        }

        for (int i = 0; i < categories.size(); i++) {
            String category = categories.get(i);

            if (category.equals("Name")) {
                throw new ParseException(MESSAGE_CANNOT_DELETE_NAME);
            }
        }

        return new DeleteCategoryCommand(index, categories);
    }
}
