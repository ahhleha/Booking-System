package com.Kursach.bookingsystem.models;

public class User {
    private String name;
    private String email;
    private String Uid;
    public User(){}


    public User(String name) {
        this.name = name;
    }

    public User(String name, String email, String uid) {
        this.name = name;
        this.email = email;
        this.Uid = uid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }
}
