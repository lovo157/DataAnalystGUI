package application;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class MainAppFrameTest extends ApplicationTest {
	//Note this is straight from chatGPT this junit classes 
    public MainAppFrame mainAppFrame;

    @Override
    public void start(javafx.stage.Stage stage) {
        mainAppFrame = new MainAppFrame();
        mainAppFrame.start(stage);
    }

    @BeforeEach
    public void setUp() throws Exception {
        // Before each test, reset the MainAppFrame
        mainAppFrame = new MainAppFrame();
    }

    @Test
    public void testHomePanelLoadedOnInit() throws Exception {
        Field currentPanelField = MainAppFrame.class.getDeclaredField("currentPanel");
        currentPanelField.setAccessible(true);
        Pane currentPanel = (Pane) currentPanelField.get(mainAppFrame);
        assertNotNull(currentPanel, "The current panel should not be null on init.");
        assertTrue(currentPanel.getChildrenUnmodifiable().stream()
            .anyMatch(node -> node instanceof Label && ((Label) node).getText().equals("Data Analyze Hub")), "The current panel should contain 'Data Analyze Hub'");
    }

    @Test
    public void testMainLayoutChildIsHomePanel() {
        Pane currentPanel = mainAppFrame.currentPanel;
        Node mainChild = mainAppFrame.mainLayout.getChildren().get(0);
        assertEquals(currentPanel, mainChild, "The main child of the main layout should be the current panel.");
    }

    @Test
    public void testSetCurrentUser() {
        mainAppFrame.setCurrentUser("testuser");
        assertEquals("testuser", mainAppFrame.getCurrentUser(), "The current user should be set to 'testuser'");
    }
}
