package com.example.reddit20;

public class User {
    public String username;
    public String email;
    public String password;
    public String displayName;
    public String description;

    public User() {
    }

    public User(String username, String email, String password, String displayName, String description) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.description = description;
    }
}
