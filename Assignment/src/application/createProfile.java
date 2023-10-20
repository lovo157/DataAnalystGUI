package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class createProfile {
    private static UserDataHandler userDataHandler = new UserDataHandler();

    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label(message);

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        DatePicker dobPicker = new DatePicker();
        dobPicker.setPromptText("Date of Birth");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button createButton = new Button("Create Profile");
        createButton.setOnAction(e -> {
            // Code to save the user's input to your CSV file (will be implemented in UserDataHandler)
            userDataHandler.addUser(
                    nameField.getText(),
                    usernameField.getText(),
                    emailField.getText(),
                    dobPicker.getValue().toString(),
                    passwordField.getText()
            );
            window.close();
        });

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, nameField, usernameField, emailField, dobPicker, passwordField, createButton, closeButton);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 300);
        window.setScene(scene);
        window.showAndWait();
    }
}
