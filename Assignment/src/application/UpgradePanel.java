package application;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class UpgradePanel extends SwitchablePanel {
    
    public UpgradePanel(MainAppFrame frame) {
        super(frame);
        addSpecificFeatures();
    }

    @Override
    protected void addSpecificFeatures() {
        Label label = new Label("Upgrade Page");
        label.setFont(new Font("Arial", 32));
        this.getChildren().add(label);
    }
}
