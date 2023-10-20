package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AnalyticsPanel extends SwitchablePanel {

    private TableView<Post> table;
    private ObservableList<Post> data = FXCollections.observableArrayList();
    private PostDataHandler postDataHandler = new PostDataHandler();

    public AnalyticsPanel(MainAppFrame appFrame) {
        super(appFrame);

        // Heading
        Label heading = new Label("Lovo's Post");
        heading.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Table setup
        table = new TableView<>();

        TableColumn<Post, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Post, String> contentColumn = new TableColumn<>("Content");
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("content"));

        TableColumn<Post, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Post, Integer> sharesColumn = new TableColumn<>("Shares");
        sharesColumn.setCellValueFactory(new PropertyValueFactory<>("shares"));

        TableColumn<Post, Integer> likesColumn = new TableColumn<>("Likes");
        likesColumn.setCellValueFactory(new PropertyValueFactory<>("likes"));

        TableColumn<Post, String> dateTimeColumn = new TableColumn<>("DateTime");
        dateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));

        table.getColumns().addAll(idColumn, contentColumn, authorColumn, sharesColumn, likesColumn, dateTimeColumn);

        // Edit and Add Post buttons
        Button editButton = new Button("Edit");
        Button addPostButton = new Button("Add Post");
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(editButton, addPostButton);

        // Populate the table when the panel is loaded
        displayPosts();

        // Add all elements to the VBox
        this.getChildren().addAll(heading, table, buttonBox);
    }

    private void displayPosts() {
        data.clear();
        data.addAll(postDataHandler.getPosts());
        table.setItems(data);
    }

    @Override
    protected void addSpecificFeatures() {
        // You can implement and add any specific functionalities you want for this panel here
    }
}
