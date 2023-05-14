package com.example.shopfruits.Models;

public class ThongKeModel {

    private int thang;
    private int doanhthuthang;

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getDoanhthuthang() {
        return doanhthuthang;
    }

    public void setDoanhthuthang(int doanhthuthang) {
        this.doanhthuthang = doanhthuthang;
    }

    public ThongKeModel(int thang, int doanhthuthang) {
        this.thang = thang;
        this.doanhthuthang = doanhthuthang;
    }

    public ThongKeModel(){}
}
