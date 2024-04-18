package com.example.luckybet;

import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private String login;
    private String password;
    private String email;
    private String fname;
    private String lname;
    private String phone;
    private String balance;

    // Constructor
    public User(String login, String password, String email, String fname, String lname, String phone, String balance) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.balance = balance;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public static User UserError()
    {
        User user;
        return user = new User("error", "error", "error", "error", "error", "error", "0");
    }
}
