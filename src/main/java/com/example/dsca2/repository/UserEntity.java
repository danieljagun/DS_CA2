package com.example.dsca2.repository;

import jakarta.persistence.*;

@Entity
@Table()
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String userID;

    private String user;
    private String password;

    public UserEntity() {
    }

    public UserEntity(String userID, String user, String password) {
        this.userID = userID;
        this.user = user;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userID='" + userID + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
