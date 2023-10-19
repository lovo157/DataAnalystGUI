package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GUI extends Application {

    Stage window;
    TableView<Book> table;
    ObservableList<Book> data = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        Button button = new Button("Show Database");
        button.setOnAction(e -> displayDatabase());

        table = new TableView<>();
        
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        table.getColumns().addAll(titleColumn, authorColumn);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(button, table);
        Scene scene = new Scene(layout, 600, 600);

        window.setScene(scene);
        window.setTitle("Database Viewer");
        window.show();
    }

    private void displayDatabase() {
        data.clear();  // Clear existing data

        String dbPath = "jdbc:sqlite:C:/Users/Isaac/Desktop/db/mydb";
        try (Connection connection = DriverManager.getConnection(dbPath);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT title, author FROM titles");
            while (resultSet.next()) {
                data.add(new Book(resultSet.getString("title"), resultSet.getString("author")));
            }
            table.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class Book {
        private String title;
        private String author;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }
    }
}
