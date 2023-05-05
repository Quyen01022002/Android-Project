package com.example.shopfruits.Models;

import java.io.Serializable;

public class User implements Serializable {

    private int userID;


    private String name;


    private String email;


    private String phone;


    private String salt;

    private String hash_password;
    private String role;
    private String avatar;
    private float point;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHash_password() {
        return hash_password;
    }

    public void setHash_password(String hash_password) {
        this.hash_password = hash_password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    public User() {
    }

    public User(int userID, String name, String email, String phone, String salt, String hash_password, String role, String avatar, float point) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.salt = salt;
        this.hash_password = hash_password;
        this.role = role;
        this.avatar = avatar;
        this.point = point;
    }
}
