package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {

    Stage window;
    Scene scene1,scene2;

    public static void main(String[] args) {
        launch(args);
    }

    sdsd
    @Override
    public void start(Stage primaryStage) throws Exception {
       window = primaryStage;
       
       Label label1 = new Label("welcome to the first scence!");

       Button  button1 = new Button("go to scence 2");
       button1.setOnAction(e -> window.setScene(scene2));
       
       //layout 1
       VBox layout1 = new VBox(20);
       layout1.getChildren().addAll(label1, button1);
       scene1 = new Scene(layout1, 200, 200);
       
       //button 2
       Button  button2 = new Button("go to scence 1");
       button2.setOnAction(e -> window.setScene(scene1));
       
       //layout 2
       StackPane layout2 = new StackPane();
       layout2.getChildren().add(button2);
       scene2 = new Scene(layout2, 600, 600);
       
       window.setScene(scene1);
       window.setTitle("dick here for both");
       window.show();
      
       
       
    }
}
