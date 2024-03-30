package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCategoryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Entry;
import seedu.address.model.person.EntryList;



public class AddCategoryCommandParserTest {

    private final AddCategoryCommandParser parser = new AddCategoryCommandParser();

    @Test
    public void parse_validArgs_returnsAddCategoryCommand() throws ParseException {
        String args = "1 " + CliSyntax.PREFIX_CATEGORY + "category1 " + CliSyntax.PREFIX_DESCRIPTION + "description1";
        EntryList e = new EntryList();
        e.add(new Entry("category1", "description1"));
        AddCategoryCommand expectedCommand = new AddCategoryCommand(Index.fromOneBased(1), e);
        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_missingIndex_throwsParseException() {
        String args = CliSyntax.PREFIX_CATEGORY + "category1 " + CliSyntax.PREFIX_DESCRIPTION + "description1";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_missingCategory_throwsParseException() {
        String args = "1 " + CliSyntax.PREFIX_DESCRIPTION + "description1";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_missingDescription_throwsParseException() {
        String args = "1 " + CliSyntax.PREFIX_CATEGORY + "category1";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    // Add more test cases as needed
}
