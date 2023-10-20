package application;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.api.FxToolkit;

public class CreateLoginTest extends ApplicationTest {
//Note this is straight from chatGPT this junit classes 
    private MainAppFrame mainAppFrame;
    private CreateLogin createLogin;

    @Override
    public void start(javafx.stage.Stage stage) {
        mainAppFrame = new MainAppFrame();
        mainAppFrame.start(stage);
        createLogin = new CreateLogin(stage, mainAppFrame);
    }

    @BeforeEach
    public void setUp() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(MainAppFrame.class);
    }

    @Test
    public void testSuccessfulLogin() {
        // Mock the UserDataHandler to always return true for validateLogin
        UserDataHandler mockHandler = new UserDataHandler() {
            @Override
            public boolean validateLogin(String username, String password) {
                return true;
            }
        };

        // Open the login dialog
        boolean result = createLogin.showLoginDialog();

        // Check login success
        assertTrue(result);
        assertEquals("testuser", createLogin.getLoggedInUser());

        // Check if status label shows successful login
        verifyThat("#statusLabel", hasText("Login successful!"));
    }

    @Test
    public void testUnsuccessfulLogin() {
        // Mock the UserDataHandler to always return false for validateLogin
        UserDataHandler mockHandler = new UserDataHandler() {
            @Override
            public boolean validateLogin(String username, String password) {
                return false;
            }
        };

        // Open the login dialog
        boolean result = createLogin.showLoginDialog();

        // Check login failure
        assertFalse(result);
        assertNull(createLogin.getLoggedInUser());

        // Check if status label shows login failure
        verifyThat("#statusLabel", hasText("Login failed!"));
    }
}
