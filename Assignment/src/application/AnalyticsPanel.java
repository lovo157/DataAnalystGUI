package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class AnalyticsPanel extends SwitchablePanel {

    private List<Post> posts;
    private ComboBox<String> sortComboBox;
    private ListView<Post> postsListView;

    public AnalyticsPanel(MainAppFrame frame) {
        super(frame);
        addSpecificFeatures();
    }

    @Override
    protected void addSpecificFeatures() {
        posts = loadPostsFromCSV("post.csv");
        updatePostsDisplay();

        // Title setup
        Label label = new Label("AnalyticsPanel");
        label.setFont(new Font("Arial", 32));
        label.setPadding(new Insets(10));
        this.getChildren().add(label);

        // Sort options
        ObservableList<String> options = FXCollections.observableArrayList("Content A-Z", "Author", "#likes", "#shares", "#Date old-recent");
        sortComboBox = new ComboBox<>(options);
        sortComboBox.setOnAction(e -> {
            sortPosts(sortComboBox.getValue());
            updatePostsDisplay();
        });

        HBox sortBox = new HBox(10);
        sortBox.getChildren().add(sortComboBox);
        sortBox.setPadding(new Insets(10));
        this.getChildren().add(sortBox);
    }

    private void sortPosts(String criteria) {
        // ... [Keep your sortPosts method the same]
    }

    private void updatePostsDisplay() {
        if (postsListView == null) {
            postsListView = new ListView<>(FXCollections.observableArrayList(posts));
            ScrollPane scrollPosts = new ScrollPane(postsListView);
            scrollPosts.setFitToWidth(true);
            this.getChildren().add(scrollPosts);

            HBox buttonBox = new HBox(10);
            Button btnAdd = new Button("Add Post");
            Button btnEdit = new Button("Edit Post");
            buttonBox.getChildren().addAll(btnAdd, btnEdit);
            buttonBox.setPadding(new Insets(10));
            this.getChildren().add(buttonBox);
        } else {
            postsListView.setItems(FXCollections.observableArrayList(posts));
        }
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
    }}
