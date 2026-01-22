package com.example.talabatiapp;

public class Order {
    private int id;
    private double total;

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public Order(int id ,double total){
        this.id=id;
        this.total=total;
    }
}
