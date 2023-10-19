package application;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;

import javafx.scene.text.Font;

public class HomePannelLoggedIn {
    private String username;

    public HomePannelLoggedIn(String username) {
        this.username = username;
    }

    public BorderPane createLoggedInPanel() {
        BorderPane contentPane = new BorderPane();

        // title
        Label label = new Label("Home IS IT WORKING Page");
        label.setFont(Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 32));
        label.setAlignment(Pos.CENTER);
        contentPane.setTop(label);

        // Button for username
        Button usernameButton = new Button(username);
        HBox topPanel = new HBox();
        topPanel.getChildren().add(usernameButton);
        HBox.setHgrow(usernameButton, Priority.ALWAYS);
        contentPane.setRight(topPanel);

        // panel to display the images with titles
//        VBox imagePanel = new VBox(5);  // 5px gap
//
//        String[] imageTitles = {"Title 1", "Title 2", "Title 3", "Title 4"};
//        for (int i = 0; i < 4; i++) {
//
//            Label titleLabel = new Label(imageTitles[i]);
//            titleLabel.setAlignment(Pos.CENTER);
//
//            imagePanel.getChildren().add(titleLabel);
//        }
//
//        contentPane.setCenter(imagePanel);

        return contentPane;
    }
}
