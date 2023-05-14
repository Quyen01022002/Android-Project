package com.example.shopfruits.Models;

public class ProductOnTop5 {
    private int productID;
    private int countSold;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCountSold() {
        return countSold;
    }

    public void setCountSold(int countSold) {
        this.countSold = countSold;
    }

    public ProductOnTop5(int productID, int countSold) {
        this.productID = productID;
        this.countSold = countSold;
    }


    public ProductOnTop5(){}


    private String name;
    private String img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public ProductOnTop5(int productID, int countSold, String name, String img) {
        this.productID = productID;
        this.countSold = countSold;
        this.name = name;
        this.img = img;
    }
}
