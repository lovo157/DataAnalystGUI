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
        Label label = new Label("Home Page");
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
        VBox imagePanel = new VBox(5);  // 5px gap

        // assuming the images are stored in a folder named 'Pictures' and have names like 'Data1.jpg', 'Data2.jpg', etc.
        String[] imageTitles = {"Title 1", "Title 2", "Title 3", "Title 4"};
        for (int i = 0; i < 4; i++) {
//            Image image = new Image("Pictures/Data1.jpg");
//            ImageView imageView = new ImageView(image);
            Label titleLabel = new Label(imageTitles[i]);
            titleLabel.setAlignment(Pos.CENTER);
//            VBox singleImagePanel = new VBox(imageView, titleLabel);
//            singleImagePanel.setAlignment(Pos.CENTER);
            imagePanel.getChildren().add(titleLabel);
        }

        contentPane.setCenter(imagePanel);

        return contentPane;
    }
}
