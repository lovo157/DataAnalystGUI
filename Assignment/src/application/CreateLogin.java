package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateLogin {
    private Stage primaryStage;
    private MainAppFrame appFrame;

    public CreateLogin(Stage primaryStage, MainAppFrame appFrame) {
        this.primaryStage = primaryStage;
        this.appFrame = appFrame;
    }

    public boolean showLoginDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Login Prompt");
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        HBox userBox = new HBox(10);
        Label userLabel = new Label("User:");
        TextField userField = new TextField();
        userBox.getChildren().addAll(userLabel, userField);

        HBox passBox = new HBox(10);
        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();
        passBox.getChildren().addAll(passLabel, passField);

        Button loginButton = new Button("Login");
        Label statusLabel = new Label();

        final boolean[] loginSuccess = {false};

        loginButton.setOnAction(e -> {
            if ("1".equals(userField.getText()) && "1".equals(passField.getText())) {
                statusLabel.setText("Login successful!");
                loginSuccess[0] = true;
                appFrame.switchToLoggedInPanel(userField.getText()); // Switch to logged-in panel
                dialog.close();
            } else {
                statusLabel.setText("Login failed!");
            }
        });

        vbox.getChildren().addAll(userBox, passBox, loginButton, statusLabel);

        Scene dialogScene = new Scene(vbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.showAndWait();

        return loginSuccess[0];
    }
}