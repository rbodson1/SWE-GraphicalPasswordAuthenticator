package com.example.swe_graphicalpasswordauthenticator;

public class UserTableModel {


    private String username;
    private String password;

    public UserTableModel(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public UserTableModel() {
    }
    // toString so that you can print


    @Override
    public String toString() {
        return "UserTableModel{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
