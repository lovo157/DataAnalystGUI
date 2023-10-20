package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

public class addPost {

    private static PostDataHandler postDataHandler = new PostDataHandler();

    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label(message);

        TextField contentField = new TextField();
        contentField.setPromptText("content");

        TextField authorField = new TextField();
        authorField.setPromptText("author");

        TextField likesField = new TextField();
        likesField.setPromptText("likes");

        TextField sharesField = new TextField();
        sharesField.setPromptText("shares");

        Button createButton = new Button("Add post");
        createButton.setOnAction(e -> {
            String currentDate = LocalDate.now().toString(); // get the current date as a string
            String content = contentField.getText();
            String author = authorField.getText();
            String likes = likesField.getText();
            String shares = sharesField.getText();
            
            addPostToDataHandler(content, author, likes, shares, currentDate);
            window.close();
        });

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, contentField, authorField, likesField, sharesField, createButton, closeButton);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 300);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void addPostToDataHandler(String content, String author, String likes, String shares, String dateTime) {
        try {
            // Assuming you have the below method correctly defined in postDataHandler.
            postDataHandler.addPost(content, author, likes, shares, dateTime);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.err.println("Failed to parse likes or shares as integers.");
        }
    }
}
