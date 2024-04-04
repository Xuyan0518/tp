package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.EntryList;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 * Testing
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY, PREFIX_DESCRIPTION, PREFIX_TAG);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }
        //Check whether the String that is parsed in contains the following prefix.
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        List<String> categories = argMultimap.getAllValues(PREFIX_CATEGORY);
        List<String> descriptions = argMultimap.getAllValues(PREFIX_DESCRIPTION);
        //check for duplicate category
        if (hasDuplicates(categories)) {
            throw new ParseException(EditCommand.MESSAGE_DUPLICATE_CATEGORY);
        }
        for (String description : descriptions) {
            if (description.trim().isEmpty()) {
                throw new ParseException(EditCommand.MESSAGE_EMPTY_DESCRIPTION);
            }
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
        boolean categoryExists = !categories.isEmpty();
        boolean descriptionExists = !descriptions.isEmpty();
        //Checks if either category or description exist, the other must exist
        //Doing this allows tags to exist without both fields
        if ((categoryExists && !descriptionExists) || (!categoryExists && descriptionExists)) {
            throw new ParseException(EditCommand.ENTRY_NOT_ADDED);
        }
        //Ensure they are both of same size
        if (categories.size() != descriptions.size()) {
            throw new ParseException(EditCommand.DIFFERENT_NUMBER_CATEGORIES_DESCRIPTIONS);
        }

        if (categories.isEmpty() && !editPersonDescriptor.isAnyTagEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        EntryList entrylist = ParserUtil.parseEntries(categories, descriptions);
        editPersonDescriptor.setEntryList(entrylist);
        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;
        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    private boolean hasDuplicates(List<String> list) {
        assert list != null : "The list should not be null";
        Set<String> set = new HashSet<>();
        for (String element : list) {
            if (set.contains(element)) {
                return true; // Found a duplicate
            }
            set.add(element);
        }
        return false; // No duplicates found
    }
}
