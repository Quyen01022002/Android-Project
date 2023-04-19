package com.example.shopfruits.Models;

public class CartEnity {
    private int cartID;


    private int userID;

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public CartEnity(int cartID, int userID) {
        this.cartID = cartID;
        this.userID = userID;
    }

    public CartEnity() {
    }
}
