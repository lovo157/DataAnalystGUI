package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
                String[] values = parseCsvLine(line);
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

    private String[] parseCsvLine(String line) {
        List<String> result = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder fieldBuilder = new StringBuilder();

        for (char ch : line.toCharArray()) {
            if (inQuotes) {
                if (ch == '\"') {
                    inQuotes = false;
                } else {
                    fieldBuilder.append(ch);
                }
            } else {
                if (ch == '\"') {
                    inQuotes = true;
                } else if (ch == ',') {
                    result.add(fieldBuilder.toString());
                    fieldBuilder.setLength(0);
                } else {
                    fieldBuilder.append(ch);
                }
            }
        }
        result.add(fieldBuilder.toString());

        return result.toArray(new String[0]);
    }

    public void addPost(String content, String author, String likes, String shares, String dateTime) {
        int nextId = posts.size() + 1;
        Post newPost = new Post(nextId, content, author, Integer.parseInt(likes), Integer.parseInt(shares), dateTime);
        posts.add(newPost);
        savePosts();
    }

    public boolean removePostById(int id) {
        for (Post post : new ArrayList<>(posts)) {
            if (post.getId() == id) {
                posts.remove(post);
                savePosts();
                return true;
            }
        }
        return false;
    }

    private void savePosts() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("post.csv", false))) {
            writer.println("ID,Content,Author,Likes,Shares,DateTime");  // Header
            for (Post post : posts) {
                writer.println(post.getId() + "," + post.getContent() + "," + post.getAuthor() + "," + post.getLikes() + "," + post.getShares() + "," + post.getDateTime());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to save posts.");
        }
    }

    public void writeToCsv(String date, String event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("post.csv", true))) {
            writer.write(date + "," + event);
            System.out.println("Writing into CSV");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to write to CSV.");
        }
    }

    public List<Post> getPosts() {
        return posts;
    }
}
