package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.stream.Collectors;

public class AnalyticsPanel extends SwitchablePanel {

    private TableView<Post> table;
    private ObservableList<Post> data = FXCollections.observableArrayList();
    private PostDataHandler postDataHandler = new PostDataHandler();

    // Moved TextField and Button declarations to class-level
    private TextField postCountField;
    private Button showPostsButton;

    public AnalyticsPanel(MainAppFrame appFrame) {
        super(appFrame);

        String loggedInUser = appFrame.getCurrentUser();

        // Update heading with the logged-in user's name
        Label heading = new Label(loggedInUser + "'s Posts");
        heading.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Table setup
        setupTable();

        // Edit and Add Post buttons
        Button removeButton = new Button("Remove Post");
        removeButton.setOnAction(e -> RemovePost.display("Remove Post", "Enter the ID of the post you want to delete"));

        Button addPostButton = new Button("Add Post");
        addPostButton.setOnAction(e -> addPost.display("Create Post", "Enter your details to create a post."));
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(removeButton, addPostButton);

        postCountField = new TextField();
        postCountField.setPromptText("Enter number of posts");

        showPostsButton = new Button("Show Posts");
        showPostsButton.setOnAction(e -> displayLimitedPosts());

        HBox filterBox = new HBox(10);
        filterBox.getChildren().addAll(postCountField, showPostsButton);

        // Add all elements to the VBox
        this.getChildren().addAll(heading, filterBox, table, buttonBox);

        // Populate the table when the panel is loaded
        displayPosts();
    }

    private void setupTable() {
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
    }

    private void displayPosts() {
        data.clear();
        data.addAll(postDataHandler.getPosts());
        table.setItems(data);
    }

    private void displayLimitedPosts() {
        int postCount;
        try {
            postCount = Integer.parseInt(postCountField.getText());
            data.clear();
            data.addAll(postDataHandler.getPosts().stream().limit(postCount).collect(Collectors.toList()));
            table.setItems(data);
        } catch (NumberFormatException e) {
            // Display error or warning if the input is not a valid number
            System.out.println("Please enter a valid number.");
        }
    }

    @Override
    protected void addSpecificFeatures() {
        // You can implement and add any specific functionalities you want for this panel here
    }
//    public String getCurrentUser() {
//        return currentUser;
//    }

}
