package com.example.talabatiapp;

public class Product {
    private int productId;
    private String productName;
    private double price;
    private int quantity;
    private int image;
    private boolean availability;
    private int category_id;
    public int getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getImage(){
        return image;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        setAvailability();
    }

    public boolean isAvailabile() {
        return availability;
    }

    public void setAvailability() {
        if (quantity > 0) this.availability = true;
        else this.availability = false;
    }
    public int getCategory_id() {
        return category_id;
    }
    public Product(int productId, String productName, double price, int quantity, int category_id,int image)
    {
        this.image=image;
        this.productId=productId;
        this.productName=productName;
        this.price=price;
        this.category_id=category_id;
        this.quantity=quantity;
        setAvailability();
    }
}

