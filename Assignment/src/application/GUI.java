package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {

    Stage window;
    TableView<Post> table;
    ObservableList<Post> data = FXCollections.observableArrayList();
    PostDataHandler postDataHandler = new PostDataHandler();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        table = new TableView<>();

        TableColumn<Post, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setMinWidth(50);

        TableColumn<Post, String> contentColumn = new TableColumn<>("Content");
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
        contentColumn.setMinWidth(100);
        contentColumn.setMaxWidth(400);  // Adjust as per your requirement

        TableColumn<Post, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        authorColumn.setMinWidth(100);

        TableColumn<Post, Integer> sharesColumn = new TableColumn<>("Shares");
        sharesColumn.setCellValueFactory(new PropertyValueFactory<>("shares"));
        sharesColumn.setMinWidth(60);

        TableColumn<Post, Integer> likesColumn = new TableColumn<>("Likes");
        likesColumn.setCellValueFactory(new PropertyValueFactory<>("likes"));
        likesColumn.setMinWidth(60);

        TableColumn<Post, String> dateTimeColumn = new TableColumn<>("DateTime");
        dateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        dateTimeColumn.setMinWidth(150);

        table.getColumns().addAll(idColumn, contentColumn, authorColumn, sharesColumn, likesColumn, dateTimeColumn);

        // Making the columns resize dynamically with the table view
        idColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        contentColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.4));
        authorColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        sharesColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        likesColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        dateTimeColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));

        // Load the posts automatically
        displayPosts();

        VBox layout = new VBox(10);
        layout.getChildren().addAll(table);
        Scene scene = new Scene(layout, 800, 600);

        window.setScene(scene);
        window.setTitle("Posts Viewer");
        window.show();
    }

    private void displayPosts() {
        data.clear();
        data.addAll(postDataHandler.getPosts());
        table.setItems(data);
    }
}
