package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Entry;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withEntries(TypicalPersons.entry("Name" , "Alice"),
                    TypicalPersons.entry("Clan", "Rainbow"),
                    TypicalPersons.entry("Class", "Warrior"),
                    TypicalPersons.entry("Game", "RPG"))
            .withTag("Noob", "Lord").build();
    public static final Person BENSON = new PersonBuilder().withEntries(TypicalPersons.entry("Name", "Bob"),
                    TypicalPersons.entry("Clan", "Thunder"),
                    TypicalPersons.entry("Class", "Mage"),
                    TypicalPersons.entry("Game", "Strategy"))
            .withTag("Veteran", "Strategist").build();
    public static final Person CARL = new PersonBuilder().withEntries(TypicalPersons.entry("Name", "Carol"),
                    TypicalPersons.entry("Clan", "Sunshine"),
                    TypicalPersons.entry("Class", "Priest"),
                    TypicalPersons.entry("Game", "MMORPG"))
            .withTag("Healer", "Friendly").build();
    public static final Person DANIEL = new PersonBuilder().withEntries(TypicalPersons.entry("Name", "Dave"),
                    TypicalPersons.entry("Clan", "Shadow"),
                    TypicalPersons.entry("Class", "Rogue"),
                    TypicalPersons.entry("Game", "Stealth"))
            .withTag("Sneaky", "Assassin").build();
    public static final Person ELLE = new PersonBuilder().withEntries(TypicalPersons.entry("Name", "Ella"),
                    TypicalPersons.entry("Clan", "Flame"),
                    TypicalPersons.entry("Class", "Berserker"),
                    TypicalPersons.entry("Game", "Action"))
            .withTag("Aggressive", "Powerful").build();
    public static final Person FIONA = new PersonBuilder().withEntries(TypicalPersons.entry("Name", "Fiona"),
                    TypicalPersons.entry("Clan", "Ocean"),
                    TypicalPersons.entry("Class", "Sailor"),
                    TypicalPersons.entry("Game", "Adventure"))
            .withTag("Explorer", "Seafarer").build();
    public static final Person GEORGE = new PersonBuilder().withEntries(TypicalPersons.entry("Name", "George"),
                    TypicalPersons.entry("Clan", "Wind"),
                    TypicalPersons.entry("Class", "Archer"),
                    TypicalPersons.entry("Game", "Survival"))
            .withTag("Sharpshooter", "Resourceful").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withEntries(TypicalPersons.entry("Name", "Hoon"),
                    TypicalPersons.entry("Clan", "Mountain"),
                    TypicalPersons.entry("Class", "Monk"),
                    TypicalPersons.entry("Game", "Fantasy"))
            .withTag("Monastic", "Zen").build();
    public static final Person IDA = new PersonBuilder().withEntries(TypicalPersons.entry("Name", "Ida"),
                    TypicalPersons.entry("Clan", "Volcano"),
                    TypicalPersons.entry("Class", "Sorcerer"),
                    TypicalPersons.entry("Game", "Puzzle"))
            .withTag("Mystic", "Brainy").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withEntries(TypicalPersons.entry("Name", "Amy"),
                    TypicalPersons.entry("Clan", "Desert"),
                    TypicalPersons.entry("Class", "Explorer"),
                    TypicalPersons.entry("Game", "History"))
            .withTag("Adventurous", "Curious").build();
    public static final Person BOB = new PersonBuilder().withEntries(TypicalPersons.entry("Name", "Bob"),
                    TypicalPersons.entry("Clan", "Jungle"),
                    TypicalPersons.entry("Class", "Scientist"),
                    TypicalPersons.entry("Game", "Science"))
            .withTag("Innovative", "Smart").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    private static Entry entry(String category, String description) {
        return new Entry(category, description);
    }
}
