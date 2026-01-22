package com.example.talabatiapp;

public class Category {
    int category_id;
    String category_name;
    int imageResource;

    int market_id;

    public int GetCategoryId()
    {return category_id;}
    public void setCategoryId(int id) {
        if (id > 0) {
            this.category_id = id;
        }
    }

    public int GetMarketId()
    {return market_id;}
    public void setMarketId(int id) {
        if (id > 0) {
            this.market_id = id;
        }
    }
    public String GetCategoryName()
    {return category_name;}
    public void setCategoryName(String Name) {
        this.category_name = Name;
    }
    public int getCategoryImageResource()
    { return imageResource; }
    public void setCategoryImage(int image)
    {
        this.imageResource=image;
    }

    public Category()
    {

    }
    public Category(int category_id,String category_name,int imageResource,int Market_id)
    {
        this.category_id=category_id;
        this.category_name=category_name;
        this.imageResource=imageResource;
        this.market_id=Market_id;
    }


}
