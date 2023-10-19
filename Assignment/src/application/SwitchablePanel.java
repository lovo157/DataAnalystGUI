package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public abstract class SwitchablePanel extends VBox {

    protected ComboBox<String> switcher;
    protected MainAppFrame appFrame;

    public SwitchablePanel(MainAppFrame appFrame) {
        this.appFrame = appFrame;

        ObservableList<String> panels = FXCollections.observableArrayList(
            "HomePanelLoggedIn", "ProfilePanel", "UpgradePanel", "AnalyticsPanel", "Logout"
        );

        switcher = new ComboBox<>(panels);
        switcher.setOnAction(e -> handlePanelSwitch(switcher.getSelectionModel().getSelectedItem()));
        getChildren().add(switcher);
    }

    protected abstract void addSpecificFeatures();

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
