package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UserDataHandler {
    private Map<String, String> userCredentials = new HashMap<>();

    public UserDataHandler() {
        loadCredentials();
    }

    private void loadCredentials() {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader("name.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 5) { // Assuming the csv follows: name,username,email,DOB,password
                    userCredentials.put(values[1], values[4]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validateLogin(String username, String password) {
        return password.equals(userCredentials.get(username));
    }
    
    public void addUser(String name, String username, String email, String dob, String password) {
        try (FileWriter fw = new FileWriter("name.csv", true); 
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
             
            out.println(name + "," + username + "," + email + "," + dob + "," + password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // In the future, you can add methods like:
    // public void addUser(User user) {...}
    // public void updateUser(User user) {...}
    // ... and so on.
}
