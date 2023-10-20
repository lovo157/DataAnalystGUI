package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostDataHandler {
    private List<Post> posts = new ArrayList<>();

    public PostDataHandler() {
        loadPosts();
    }

    private void loadPosts() {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader("post.csv"))) {
            br.readLine();  // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 6) {
                    try {
                        int id = Integer.parseInt(values[0].trim());
                        String content = values[1];
                        String author = values[2];
                        int likes = Integer.parseInt(values[3].trim());
                        int shares = Integer.parseInt(values[4].trim());
                        String dateTime = values[5];
                        
                        posts.add(new Post(id, content, author, likes, shares, dateTime));
                        
                    } catch (Exception e) {
                        System.err.println("Error parsing line: " + line);
                    }
                } else {
                    System.err.println("Incorrect number of fields in line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Post> getPosts() {
        return posts;
    }
}
