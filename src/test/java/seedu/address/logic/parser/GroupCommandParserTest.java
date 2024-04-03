package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class GroupCommandParserTest {

    private final GroupCommandParser parser = new GroupCommandParser();

    @Test
    public void parse_missingCategory_throwsParseException() {
        String args = "";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_emptyCategory_throwsParseException() {
        String args = " " + CliSyntax.PREFIX_CATEGORY;
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_tooManyCategories_throwsParseException() {
        String args = " " + CliSyntax.PREFIX_CATEGORY + "category1"
                + " " + CliSyntax.PREFIX_CATEGORY + "category2";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }
}

