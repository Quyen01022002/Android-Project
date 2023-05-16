package com.example.shopfruits.Models;

import java.util.Date;

public class OrderEnity {


    private int orderID;


    private int userID;


    private int storeID;


    private String address;


    private String phone;

    private String statusTT;


    private String status;

    private Integer shipperID ;


    private int costSum;



    private Date createdAt;


    private Date updatedAt;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatusTT() {
        return statusTT;
    }

    public void setStatusTT(String statusTT) {
        this.statusTT = statusTT;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getShipperID() {
        return shipperID;
    }

    public void setShipperID(Integer shipperID) {
        this.shipperID = shipperID;
    }

    public int getCostSum() {
        return costSum;
    }

    public void setCostSum(int costSum) {
        this.costSum = costSum;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public OrderEnity() {
    }
}
