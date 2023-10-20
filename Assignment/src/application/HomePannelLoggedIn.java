package application;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.Font;

class HomePanelLoggedIn extends SwitchablePanel {

    public HomePanelLoggedIn(MainAppFrame frame) {
        super(frame);
        addSpecificFeatures();
    }

    @Override
    protected void addSpecificFeatures() {
        // layout setup
        BorderPane contentPane = new BorderPane();

        // title
        String loggedInUser = appFrame.getCurrentUser();
        Label label = new Label(loggedInUser+ "`s home Page");
        label.setFont(new Font("Arial", 32));
        
        String welcomeText = "Hey there!\n"
                + "This page was looking a bit... yawn-worthy. So, we're jazzing it up!\n"
                + "Use the dropdown box to explore. And for our post-makers: that's your spot to add some spice!\n"
                + "Happy browsing (and chuckling)!\n";

        // Create the label with the text
        Label welcomeLabel = new Label(welcomeText);

        // Style the label (optional)
        welcomeLabel.setStyle("-fx-padding: 20px; -fx-background-color: #f4f4f4; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-font-size: 14px;");
        

        label.setAlignment(javafx.geometry.Pos.CENTER);
        contentPane.setTop(label);


        // Add the contentPane to the children of SwitchablePanel.
        getChildren().addAll(contentPane,welcomeLabel);
    }
}
