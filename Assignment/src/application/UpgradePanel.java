package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpgradePanel extends SwitchablePanel {

    private Button paywallButton;
    private PieChart sharesChart;
    private List<Post> posts;

    public UpgradePanel(MainAppFrame appFrame) {
        super(appFrame);

        // Add upgrade features only after "payment"
        
        
        paywallButton = new Button("Subscribe for $0");
        paywallButton.setStyle("-fx-font-size: 20px; -fx-padding: 10px 20px;"); 
        paywallButton.setOnAction(e -> handlePayment());

        // Center the button using VBox
       
        this.setAlignment(Pos.CENTER);
        getChildren().add(paywallButton);
    }

    private void handlePayment() {
        // Mock payment system
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Thank you for subscribing!");
        alert.showAndWait();

        // Remove paywall button
        getChildren().remove(paywallButton);

        // Load posts from CSV
        posts = loadPostsFromCSV("bulk.csv");

        // Generate the pie chart
        generatePieChart();
       
    }

    private void generatePieChart() {
        int lowShares = 0, medShares = 0, highShares = 0;
        for (Post post : posts) {
            int shares = post.getShares();
            if (shares < 100) lowShares++;
            else if (shares < 999) medShares++;
            else highShares++;
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("0-100 shares", lowShares),
                new PieChart.Data("100-999 shares", medShares),
                new PieChart.Data("1000+ shares", highShares));
        sharesChart = new PieChart(pieChartData);
        getChildren().add(sharesChart);
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

	@Override
	protected void addSpecificFeatures() {
		// TODO Auto-generated method stub
		
	}
}
