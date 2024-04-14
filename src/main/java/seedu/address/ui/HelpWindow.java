package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {
    //testing
    public static final String USERGUIDE_URL = "https://ay2324s2-cs2103t-w13-3.github.io/tp/UserGuide.html";

    public static final String DASH = "----------------------------------------"
           + "--------------------------------------\n";

    public static final String HELP_MENU = "Here is a list of commands and their examples.\n"
            + DASH
            + "Add: add n/NAME [t/TAG]…\n"
            + "e.g. add n/James Ho t/friend t/colleague\n"
            + DASH
            + "Add a Category: addCategory INDEX [c/CATEGORY] [d/DESCRIPTION]\n"
            + "e.g. addCategory 1 c/class d/warrior\n"
            + DASH
            + "Delete a Category: deleteCategory INDEX [c/CATEGORY]\n"
            + "e.g. deleteCategory 1 c/class\n"
            + DASH
            + "Edit a category: edit INDEX [c/CATEGORY] [d/DESCRIPTION]\n"
            + "Or\n"
            + "edit INDEX [t/TAG]"
            + "e.g. edit 2 c/clan d/rainbow\n"
            + "e.g. edit 1 t/warrior t/mage\n"
            + DASH
            + "Find: find KEYWORD [MORE_KEYWORDS]\n"
            + "e.g. find James Jake\n"
            + DASH
            + "Delete an entry: delete INDEX\n"
            + "e.g. delete 3\n"
            + DASH
            + "Group: group [c/CATEGORY]\n"
            + "e.g. group c/clans\n"
            + DASH
            + "List: list\n"
            + DASH
            + "Undo: undo\n"
            + DASH
            + "Redo: redo\n"
            + DASH
            + "Clear: clear\n"
            + DASH
            + "Help: help\n"
            + DASH
            + "Exit: exit";

    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private Label helpMenuLabel;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        helpMenuLabel.setText(HELP_MENU);
        helpMessage.prefWidthProperty().bind(helpMenuLabel.widthProperty());
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
    @FXML
    private void mouseClicked(MouseEvent event) {
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();
            // Change the button's background color
            button.setStyle("-fx-background-color: #f09905;"); // Change to your desired color
        }
    }

    @FXML
    private void mouseReleased(MouseEvent event) {
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();
            // Change the button's background color
            button.setStyle("-fx-background-color: #d6b47a;"); // Change to your desired color
        }
    }

}
