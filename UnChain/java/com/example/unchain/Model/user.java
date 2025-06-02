package com.example.unchain.Model;

public class user {

    private String Email, Password, Name;

    public user(String email, String password, String name) {
        Email = email;
        Password = password;
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
