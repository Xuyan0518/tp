package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import seedu.address.model.person.Entry;
import seedu.address.model.person.EntryList;
import seedu.address.model.person.Person;
/**
 * A UI component that displays information of a {@code Group}.
 */
public class GroupCard extends UiPart<Region> {

    private static final String FXML = "GroupCard.fxml";
    private static final String FONT_PATH = "../../resources/view/Pixel_font/PressStart2P-Regular.ttf";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person group;

    @FXML
    private Label groupNameLabel;
    @FXML
    private Label nameListLabel;

    /**
     * Creates a Group
     */
    public GroupCard(Person group) {
        super(FXML);
        this.group = group;
        String text = "";
        EntryList e = group.getList();
        for (int i = 0; i < e.size(); i++) {
            Entry entry = e.get(i);
            if (!entry.getCategory().equals("Group Name")) {
                text = text + e.get(i).toString() + "\n";
            }
        }
        Entry groupNameEntry = group.getEntry("Group Name");
        if (groupNameEntry != null) {
            groupNameLabel.setText(groupNameEntry.toString());
        } else {
            groupNameLabel.setText("");
        }
        groupNameLabel.setFont(Font.loadFont(FONT_PATH, 120));
        nameListLabel.setText(text);
        nameListLabel.setFont(Font.loadFont(FONT_PATH, 120));
    }
}
