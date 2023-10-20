package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;

public class RemovePost {

    public static void display(String title, String message) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText(message);

        PostDataHandler dataHandler = new PostDataHandler();

        dialog.showAndWait().ifPresent(idStr -> {
            try {
                int postId = Integer.parseInt(idStr);
                boolean success = dataHandler.removePostById(postId);
                Alert alert = new Alert(AlertType.INFORMATION);
                if (success) {
                    alert.setContentText("Post successfully removed.");
                } else {
                    alert.setContentText("Post not found.");
                }
                alert.showAndWait();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Please enter a valid post ID.");
                alert.showAndWait();
            }
        });
    }
}
