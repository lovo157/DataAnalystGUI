package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class SwitchablePanel extends VBox {

    protected ComboBox<String> switcher;
    protected MainAppFrame appFrame;
    protected Label currentPanelLabel; 
    protected Button usernameButton;  // New username button
    protected HBox topPanel;  // HBox for holding the switcher

    public SwitchablePanel(MainAppFrame appFrame) {
        this.appFrame = appFrame;

        ObservableList<String> panels = FXCollections.observableArrayList(
            "HomePanelLoggedIn", "ProfilePanel", "UpgradePanel", "AnalyticsPanel", "Logout"
        );

        switcher = new ComboBox<>(panels);
        switcher.setOnAction(e -> handlePanelSwitch(switcher.getSelectionModel().getSelectedItem()));
        
        // Create HBox for switcher
        topPanel = new HBox(switcher);
        
        // Add username feature
        String loggedInUser = appFrame.getCurrentUser();
        		
        usernameButton = new Button(loggedInUser);
      
        HBox userPanel = new HBox(usernameButton);
        userPanel.setAlignment(javafx.geometry.Pos.TOP_RIGHT);

        getChildren().addAll(topPanel, userPanel);

        // Initialize and add the current panel label
        currentPanelLabel = new Label();
        getChildren().add(currentPanelLabel);

        updateCurrentPanelLabel();
    }
  

    protected abstract void addSpecificFeatures();

    // New method to update the label with the current panel's name
    protected void updateCurrentPanelLabel() {
        switcher.setValue(this.getClass().getSimpleName()); // Set ComboBox selection to the current panel
    }

    private void handlePanelSwitch(String selectedItem) {
        switch (selectedItem) {
            case "HomePanelLoggedIn":
                appFrame.setCurrentPanel(new HomePanelLoggedIn(appFrame));
                break;
            case "ProfilePanel":
                appFrame.setCurrentPanel(new ProfilePanel(appFrame));
                break;
            case "UpgradePanel":
                appFrame.setCurrentPanel(new UpgradePanel(appFrame));
                break;
            case "AnalyticsPanel":
                appFrame.setCurrentPanel(new AnalyticsPanel(appFrame));
                break;
            case "Logout":
                appFrame.switchToHomePanel();
                return;
            default:
                throw new IllegalStateException("Unexpected panel: " + selectedItem);
        }
    }
}
