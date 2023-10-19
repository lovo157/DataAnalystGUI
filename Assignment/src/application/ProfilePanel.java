package application;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class ProfilePanel extends SwitchablePanel {
    
    public ProfilePanel(MainAppFrame frame) {
        super(frame);
        addSpecificFeatures();
    }

    @Override
    protected void addSpecificFeatures() {
        Label label = new Label("Profile Page");
        label.setFont(new Font("Arial", 32));
        this.getChildren().add(label);
    }
}
