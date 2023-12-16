package com.example.dsca2.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userID;

    private String name;
    private String password;

    public User() {
    }

    public User(Long userID, String name, String password) {
        this.userID = userID;
        this.name = name;
        this.password = password;
    }

    public Long getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userID='" + userID + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
