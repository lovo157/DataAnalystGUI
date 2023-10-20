package application;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ProfilePanel extends SwitchablePanel {

    private static final double LABEL_WIDTH = 150; // Adjust as needed for longest label

    // Profile-specific UI components
    private Label nameLabel, usernameLabel, emailLabel, dobLabel, passwordLabel;
    private TextField nameField, usernameField, emailField;
    private PasswordField passwordField;
    private DatePicker dobPicker;
    private Button saveProfileButton;

    public ProfilePanel(MainAppFrame appFrame) {
        super(appFrame);

   

        // Initialize profile-specific features
        addSpecificFeatures();
    }

    @Override
    protected void addSpecificFeatures() {
        // Profile name
        nameLabel = createLabel("Name:");
        nameField = new TextField();
        HBox nameBox = new HBox(10, nameLabel, nameField);

        // Profile username
        usernameLabel = createLabel("Username:");
        usernameField = new TextField();
        HBox usernameBox = new HBox(10, usernameLabel, usernameField);

        // Profile email
        emailLabel = createLabel("Email:");
        emailField = new TextField();
        HBox emailBox = new HBox(10, emailLabel, emailField);

        // Date of birth
        dobLabel = createLabel("Date of Birth:");
        dobPicker = new DatePicker();
        HBox dobBox = new HBox(10, dobLabel, dobPicker);

        // Password
        passwordLabel = createLabel("Password:");
        passwordField = new PasswordField();
        HBox passwordBox = new HBox(10, passwordLabel, passwordField);

        // Save profile button
        saveProfileButton = new Button("Save Profile");
        saveProfileButton.setOnAction(e -> handleSaveProfile());

        VBox profileBox = new VBox(15, nameBox, usernameBox, emailBox, dobBox, passwordBox, saveProfileButton);
        profileBox.setPadding(new Insets(20));

        getChildren().add(profileBox);
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setMinWidth(LABEL_WIDTH); // Ensuring that all labels have the same width
        label.setFont(new Font(18));
        return label;
    }

    private void handleSaveProfile() {
        // Logic for saving profile data
        System.out.println("Name: " + nameField.getText());
        System.out.println("Username: " + usernameField.getText());
        System.out.println("Email: " + emailField.getText());
        System.out.println("Date of Birth: " + dobPicker.getValue());
        System.out.println("Password: " + passwordField.getText());
    }
}
