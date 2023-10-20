package application;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class UpgradePanelTest extends ApplicationTest {
	//Note this is straight from chatGPT this junit classes 
    private UpgradePanel upgradePanel;
    
    public void start(Stage stage) throws Exception {
        MainAppFrame appFrame = new MainAppFrame(); // Assuming MainAppFrame has a default constructor or provide necessary arguments
        upgradePanel = new UpgradePanel(appFrame);
        stage.setScene(upgradePanel);
        stage.show();
    }

    @BeforeEach
    public void setUp() throws Exception {
        // Before each test, reset the UpgradePanel
        upgradePanel = new UpgradePanel(new MainAppFrame()); 
    }

    @Test
    public void testPaywallButtonPresentOnInit() {
        Button foundButton = lookup(".button").queryButton();
        assertNotNull(foundButton, "Paywall button should be present on init.");
        assertEquals("Subscribe for $0", foundButton.getText(), "Button text should match.");
    }

    @Test
    public void testPaywallButtonRemovedAfterPayment() {
        upgradePanel.handlePayment();
        Button foundButton = lookup(".button").queryButton();
        assertNull(foundButton, "Paywall button should be removed after payment.");
    }

    @Test
    public void testUserCountLabelAfterPayment() {
        // Assuming you have a sample 'bulk.csv' for test with 5 users
        upgradePanel.handlePayment();
        Label userCountLabel = lookup(".label").query();
        assertNotNull(userCountLabel, "User count label should be present.");
        assertEquals("User count: 5", userCountLabel.getText(), "User count should match the sample CSV data.");
    }
}
