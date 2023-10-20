package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainAppFrame extends Application {
    private VBox mainLayout;
    private Pane currentPanel;
    private String currentUser = null;
    private Stage mainStage;

    @Override
    public void start(Stage primaryStage) {
        this.mainStage = primaryStage;

        mainLayout = new VBox();
        currentPanel = createHomePanel(primaryStage);
        mainLayout.getChildren().add(currentPanel);

        primaryStage.setTitle("App");
        primaryStage.setScene(new Scene(mainLayout, 800, 600));
        primaryStage.show();
    }

    private Pane createHomePanel(Stage primaryStage) {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(10));

        // Top Panel with Heading 
        HBox topPanel = new HBox(10);
        Label label1 = new Label("Data Analyze Hub");

        // Buttons
        Button createAccountButton = new Button("Create Account");
        createAccountButton.setOnAction(e -> createProfile.display("Create Profile", "Enter your details to create a profile."));
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> showLoginDialog()); // Added this to show the login dialog

        Button logoutButton = new Button("Logout");
        logoutButton.setVisible(false);

        topPanel.getChildren().addAll(label1, createAccountButton, loginButton, logoutButton);
        HBox.setHgrow(label1, Priority.ALWAYS);
        topPanel.setAlignment(Pos.CENTER_LEFT);

        Label labelText = new Label("Our Data Analyze Hub is designed to transform how you perceive social media...");
        labelText.setWrapText(true);

        panel.getChildren().addAll(topPanel, labelText);
        return panel;
    }

    private void showLoginDialog() {
        CreateLogin login = new CreateLogin(mainStage, this);
        boolean isSuccess = login.showLoginDialog();
        if (isSuccess) {
            switchToLoggedInPanel("username"); // Switches to the logged-in panel upon successful login
        }
    }

    public void switchToLoggedInPanel(String username) {
        SwitchablePanel homePanel = new HomePanelLoggedIn(this);
        setCurrentPanel(homePanel);
    }

    public void setCurrentPanel(Pane newPanel) {
        if (currentPanel != null) {
            mainLayout.getChildren().remove(currentPanel);
        }
        currentPanel = newPanel;
        mainLayout.getChildren().add(currentPanel);
    }

    public void switchToHomePanel() {
        Pane homePanel = createHomePanel(mainStage);
        setCurrentPanel(homePanel);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
