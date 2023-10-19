package application;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

        // username button
        Button usernameButton = new Button("username");
        HBox topPanel = new HBox();
        topPanel.getChildren().add(usernameButton);
        topPanel.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        contentPane.setRight(topPanel);
    }
}