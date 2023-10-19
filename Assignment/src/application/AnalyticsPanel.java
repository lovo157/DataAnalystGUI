package application;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class AnalyticsPanel extends SwitchablePanel {
    
    public AnalyticsPanel(MainAppFrame frame) {
        super(frame);
        addSpecificFeatures();
    }

    @Override
    protected void addSpecificFeatures() {
        Label label = new Label("Analytics Page");
        label.setFont(new Font("Arial", 32));
        this.getChildren().add(label);
    }
}
