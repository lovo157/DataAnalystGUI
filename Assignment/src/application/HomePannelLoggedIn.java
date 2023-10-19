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
        Label label = new Label("Home Page");
        label.setFont(new Font("Arial", 32));

        label.setAlignment(javafx.geometry.Pos.CENTER);
        contentPane.setTop(label);


        // Add the contentPane to the children of SwitchablePanel.
        getChildren().add(contentPane);
    }
}
