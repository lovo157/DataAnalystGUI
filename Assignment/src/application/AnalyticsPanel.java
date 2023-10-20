package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class AnalyticsPanel extends SwitchablePanel {

    private ObservableList<Post> posts;
    private ComboBox<String> sortComboBox;
    private TableView<Post> postsTableView;

    public AnalyticsPanel(MainAppFrame frame) {
        super(frame);
        addSpecificFeatures();
    }

    @Override
    protected void addSpecificFeatures() {
        posts = FXCollections.observableArrayList(loadPostsFromCSV("post.csv"));
        
        // Title setup
        Label label = new Label("AnalyticsPanel");
        label.setFont(new Font("Arial", 32));
        label.setPadding(new Insets(10));
        this.getChildren().add(label);

        // Table setup
        postsTableView = new TableView<>();

        TableColumn<Post, Integer> postIDColumn = new TableColumn<>("Post ID");
        postIDColumn.setCellValueFactory(new PropertyValueFactory<>("postID"));

        // Content column
        TableColumn<Post, String> contentColumn = new TableColumn<>("Content");
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("content"));

        // Author column
        TableColumn<Post, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        // Likes column
        TableColumn<Post, Integer> likesColumn = new TableColumn<>("Likes");
        likesColumn.setCellValueFactory(new PropertyValueFactory<>("likes"));

        // Shares column
        TableColumn<Post, Integer> sharesColumn = new TableColumn<>("Shares");
        sharesColumn.setCellValueFactory(new PropertyValueFactory<>("shares"));

        // Assuming you have a TableView instance named 'table'
        table.getColumns().addAll(postIDColumn, contentColumn, authorColumn, likesColumn, sharesColumn);

////        this.getChildren().add(postsTableView);
//
////        // Sort options
////        ObservableList<String> options = FXCollections.observableArrayList("Content A-Z", "Author", "#likes", "#shares", "#Date old-recent");
////        sortComboBox = new ComboBox<>(options);
////        sortComboBox.setOnAction(e -> {
////            sortPosts(sortComboBox.getValue());
////            updatePostsDisplay();
////        });
////
////        HBox sortBox = new HBox(10);
////        sortBox.getChildren().add(sortComboBox);
////        sortBox.setPadding(new Insets(10));
////        this.getChildren().add(sortBox);
////    }

    private void sortPosts(String criteria) {
        // ... [Keep your sortPosts method the same]
    }

    private void updatePostsDisplay() {
        postsTableView.setItems(posts);
    }

    private List<Post> loadPostsFromCSV(String filename) {
        List<Post> loadedPosts = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            br.readLine(); // skip header

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                
                if (values.length != 6) continue;

                try {
                    loadedPosts.add(new Post(
                        Integer.parseInt(values[0].trim().replace("\"", "")),
                        values[1].trim().replace("\"", ""),
                        values[2].trim().replace("\"", ""),
                        Integer.parseInt(values[3].trim().replace("\"", "")),
                        Integer.parseInt(values[4].trim().replace("\"", "")),
                        values[5].trim().replace("\"", "")
                    ));
                } catch (NumberFormatException ignored) {
                    System.err.println("Error parsing line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadedPosts;
    }
}
