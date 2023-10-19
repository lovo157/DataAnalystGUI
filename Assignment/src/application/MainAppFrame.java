package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainAppFrame extends Application {
    private Pane currentPanel;
    private String currentUser = null;
    
    @Override
    public void start(Stage primaryStage) {
        currentPanel = createHomePanel();
        primaryStage.setTitle("App");
        primaryStage.setScene(new Scene(currentPanel, 800, 600));
        primaryStage.show();
        showLoginDialog(primaryStage);
    }

    private Pane createHomePanel() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(10));

        // Top Panel with Heading 
        HBox topPanel = new HBox(10);
        Label label1 = new Label("Data Analyze Hub");

        //buttons
        Button createAccountButton = new Button("Create Account");
        createAccountButton.setOnAction(e -> createProfile.display("create Account", "wow this is awesome"));

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> showLoginDialog(null)); // Change this to handle a proper re-login mechanism
        Button logoutButton = new Button("Logout");
        logoutButton.setVisible(false);

        topPanel.getChildren().addAll(label1, createAccountButton, loginButton, logoutButton);
        HBox.setHgrow(label1, Priority.ALWAYS);
        topPanel.setAlignment(Pos.CENTER_LEFT);

        Label labelText = new Label("Our Data Analyze Hub is designed to transform how you perceive social media...");
        labelText.setWrapText(true);

//        ImageView imageViewNorth = new ImageView(new Image("file:Pictures/Data1.jpg"));
//        ImageView imageViewSouth = new ImageView(new Image("file:Pictures/Data2.jpg"));
//add images below when working
        panel.getChildren().addAll(topPanel, labelText);
        return panel;
    }
    private void showLoginDialog(Stage primaryStage) {
        CreateLogin login = new CreateLogin(primaryStage, this);
        if(login.showLoginDialog()) {
            currentUser = "username"; // Update this accordingly
            switchToLoggedInPanel();
        }
    }

    private void switchToLoggedInPanel() {
        HomePannelLoggedIn homeLoggedIn = new HomePannelLoggedIn(currentUser);
        currentPanel = homeLoggedIn.createLoggedInPanel();
        
        if (currentPanel.getScene() != null) {
            Stage stage = (Stage) currentPanel.getScene().getWindow();
            stage.setScene(new Scene(currentPanel, 800, 600));
        } else {
            System.err.println("Error: Current panel is not attached to any scene.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}