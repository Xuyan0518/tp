package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.FindCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonFieldsContainKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for FindCommand.
 */
public class FindCommandTest {
    private Model model;
    private Model expectedModel;
    /**
     * Sets up the model and expectedModel with a typical address book before each test.
     * This ensures a consistent starting state for tests.
     */
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }
    /**
     * Tests the equality of the FindCommand. Verifies that:
     * - A FindCommand equals itself.
     * - Two FindCommand objects with the same predicate are equal.
     * - A FindCommand is not equal to null.
     * - A FindCommand is not equal to an object of a different type.
     * - Two FindCommand objects with different predicates are not equal.
     */
    @Test
    public void equals() {
        HashMap<String, List<String>> categoryDescriptionMap = new HashMap<>();
        categoryDescriptionMap.put("LOL", Arrays.asList("King"));
        PersonFieldsContainKeywordPredicate firstPredicate =
            new PersonFieldsContainKeywordPredicate(categoryDescriptionMap, new HashSet<>());
        PersonFieldsContainKeywordPredicate secondPredicate =
            new PersonFieldsContainKeywordPredicate(new HashMap<>(), new HashSet<>(Arrays.asList("friend")));
        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommand));
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
        assertFalse(findFirstCommand.equals(1));
        assertFalse(findFirstCommand.equals(null));
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }
    /**
     * Tests the behavior of FindCommand with no keywords. Verifies that no persons are found and the
     * resulting list is empty.
     */
    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonFieldsContainKeywordPredicate predicate = preparePredicate(" ", " ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }
    /**
     * Tests the FindCommand's ability to filter persons based on a single category and description pair.
     * Verifies that the correct person is found.
     */
    @Test
    public void execute_singleCategoryDescription_singlePersonsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonFieldsContainKeywordPredicate predicate = preparePredicate("Clan", "Rainbow");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }
    /**
     * Tests the FindCommand's functionality with multiple categories and descriptions.
     * Verifies that all matching persons are correctly identified and listed.
     */
    @Test
    public void execute_multipleCategoriesKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonFieldsContainKeywordPredicate predicate = preparePredicate(
            "Class", "Warrior", "Class", "Mage", "Class", "Priest");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredPersonList().containsAll(Arrays.asList(ALICE, BENSON, CARL)));
    }
    /**
     * Tests the FindCommand's handling of non-existing category and description pairs.
     * Verifies that no persons are found when the specified category and description do not match any person.
     */
    @Test
    public void execute_nonExistingCategoryAndDescription_noPersonFound() {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonFieldsContainKeywordPredicate predicate = preparePredicate("NilCategory", "NilDescription");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredPersonList().isEmpty());
    }
    /**
     * Tests the FindCommand's ability to filter persons based on one tag.
     * Verifies that the correct person is found when valid tag is used
     */
    @Test
    public void execute_singleValidTag_singlePersonFound() {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        Set<String> tags = new HashSet<>(Arrays.asList("Noob"));
        PersonFieldsContainKeywordPredicate predicate = new PersonFieldsContainKeywordPredicate(new HashMap<>(), tags);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }
    /**
     * Tests the FindCommand's ability to filter persons based on multiple tags.
     * Verifies that the correct persons are found when valid tags are used.
     */
    @Test
    public void execute_multipleValidTags_multiplePersonsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        Set<String> tags = new HashSet<>(Arrays.asList("Noob", "Veteran"));
        PersonFieldsContainKeywordPredicate predicate = new PersonFieldsContainKeywordPredicate(new HashMap<>(), tags);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }
    /**
     * Tests the parser for FindCommand to throw exception when
     * there is unequal number of categories and descriptions.
     */
    @Test
    public void execute_unevenCategoryDescription_parseFail() {
        String eMessage = "Invalid command format! \n" + FindCommand.COMMAND_FORMAT_CAT_DESC;
        String args = " c/class d/high c/clan d/good c/game";
        FindCommandParser parser = new FindCommandParser();
        ParseException pe = assertThrows(ParseException.class, () -> parser.parse(args));
        assertEquals(eMessage, pe.getMessage());
    }
    /**
     * Test the parser for FindCommand to throw exception when
     * there is missing category and description.
     */
    @Test
    public void execute_missingCategoryDescription_parseFail() {
        String eMessage = "Invalid command format! \n" + FindCommand.EMPTY_CAT_OR_DESC;
        String args = " c/ d/ c/clan d/rainbow";
        FindCommandParser parser = new FindCommandParser();
        ParseException pe = assertThrows(ParseException.class, () -> parser.parse(args));
        assertEquals(eMessage, pe.getMessage());
    }
    /**
     * Test the parser for FindCommand to throw exception when
     * there is missing tag.
     */
    @Test
    public void execute_missingTag_parseFail() {
        String eMessage = "Invalid command format! \n" + FindCommand.EMPTY_TAG;
        String args = " t/ t/Explorer";
        FindCommandParser parser = new FindCommandParser();
        ParseException pe = assertThrows(ParseException.class, () -> parser.parse(args));
        assertEquals(eMessage, pe.getMessage());
    }
    /**
     * Test the parser for FindCommand to throw exception when
     * there is missing tag, in the presence of category and description prefixes.
     */
    @Test
    public void execute_missingTagWithCatDesc_parseFail() {
        String eMessage = "Invalid command format! \n" + FindCommand.EMPTY_TAG;
        String args = " c/clan d/rainbow t/";
        FindCommandParser parser = new FindCommandParser();
        ParseException pe = assertThrows(ParseException.class, () -> parser.parse(args));
        assertEquals(eMessage, pe.getMessage());
    }
    /**
     * Tests the parser of FindCommand to throw exception when
     * there is unequal number of category and description in the presence of tag prefix.
     */
    @Test
    public void execute_unevenCatDescWithTag_parseFail() {
        String eMessage = "Invalid command format! \n" + FindCommand.COMMAND_FORMAT_CAT_DESC;
        String args = " c/ c/clan d/rainbow t/noob";
        FindCommandParser parser = new FindCommandParser();
        ParseException pe = assertThrows(ParseException.class, () -> parser.parse(args));
        assertEquals(eMessage, pe.getMessage());
    }
    /**
     * Tests the parser of FindCommand to throw exception when
     * there is missing category and description in the presence of tag prefix.
     */
    @Test
    public void execute_missingCatDescWithTag_parseFail() {
        String eMessage = "Invalid command format! \n" + FindCommand.EMPTY_CAT_OR_DESC;
        String args = " c/ d/ t/noob";
        FindCommandParser parser = new FindCommandParser();
        ParseException pe = assertThrows(ParseException.class, () -> parser.parse(args));
        assertEquals(eMessage, pe.getMessage());
    }
    /**
     * Tests the parser of FindCommand to throw exception when
     * there tag, category and description do not form a trio.
     */
    @Test
    public void execute_catDescTagNotATrio_parseFail() {
        String eMessage = "Invalid command format! \n" + FindCommand.COMMAND_CAT_DESC_TAG;
        String args = " c/clan d/rainbow t/noob c/class d/priest";
        FindCommandParser parser = new FindCommandParser();
        ParseException pe = assertThrows(ParseException.class, () -> parser.parse(args));
        assertEquals(eMessage, pe.getMessage());
    }
    /**
     * Helper method to prepare a {@link PersonFieldsContainKeywordPredicate} based on the provided category
     * and description pairs. Facilitates easier creation of predicates for testing.
     *
     * @param categoryDescriptionPairs Varargs input where each pair of strings represent
     *     a category and its corresponding description.
     * @return A {@code PersonFieldsContainKeywordPredicate} created from the specified category and description pairs.
     */
    private PersonFieldsContainKeywordPredicate preparePredicate(String... categoryDescriptionPairs) {
        Map<String, List<String>> categoryDescriptionMap = new HashMap<>();
        for (int i = 0; i < categoryDescriptionPairs.length; i += 2) {
            String category = categoryDescriptionPairs[i];
            String description = categoryDescriptionPairs[i + 1];
            categoryDescriptionMap.computeIfAbsent(category, k -> new ArrayList<>()).add(description);
        }
        return new PersonFieldsContainKeywordPredicate(categoryDescriptionMap, new HashSet<>());
    }
}
