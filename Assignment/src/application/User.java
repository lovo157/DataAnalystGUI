package application;

public class User {
    private String name;
    private String username;
    private String email;
    private String dob;
    private String password;
    private boolean subscribed;

    public User(String name, String username, String email, String dob, String password, boolean subscribed) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.dob = dob;
        this.password = password;
        this.subscribed = subscribed;
    }

 
    public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    
    public String toString() {
        return name + "," + username + "," + email + "," + dob + "," + password + "," + subscribed;
    }
}
