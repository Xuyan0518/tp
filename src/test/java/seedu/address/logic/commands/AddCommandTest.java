package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Entry;
import seedu.address.model.person.EntryList;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void execute_validPersonWithoutEntry_success() throws CommandException {
        Model model = new ModelManager();
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson, null);

        CommandResult commandResult = addCommand.execute(model);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_validPersonWithEntry_success() throws CommandException {
        Model model = new ModelManager();
        EntryList entryList = new EntryList();
        entryList.add(new Entry("Category", "Description"));
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson, entryList);

        CommandResult commandResult = addCommand.execute(model);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_validPersonWithMultipleEntries_success() throws CommandException {
        Model model = new ModelManager();
        EntryList entryList = new EntryList();
        entryList.add(new Entry("Category1", "Description1"));
        entryList.add(new Entry("Category2", "Description2"));
        entryList.add(new Entry("Category3", "Description3"));
        Person validPerson = new PersonBuilder().withEntries(entryList.get(0), entryList.get(1),
                entryList.get(2)).build();
        AddCommand addCommand = new AddCommand(validPerson, entryList);

        CommandResult commandResult = addCommand.execute(model);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_validPersonWithCategoryWithoutDescription_success() throws CommandException {
        Model model = new ModelManager();
        EntryList entryList = new EntryList();
        entryList.add(new Entry("Category1", "")); // Empty description
        Person validPerson = new PersonBuilder().withEntries(entryList.get(0)).build();
        AddCommand addCommand = new AddCommand(validPerson, entryList);

        CommandResult commandResult = addCommand.execute(model);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_validPersonWithDescriptionWithoutCategory_success() throws CommandException {
        Model model = new ModelManager();
        EntryList entryList = new EntryList();
        entryList.add(new Entry("", "Description1")); // Empty category
        Person validPerson = new PersonBuilder().withEntries(entryList.get(0)).build();
        AddCommand addCommand = new AddCommand(validPerson, entryList);

        CommandResult commandResult = addCommand.execute(model);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                commandResult.getFeedbackToUser());
    }

}

