package com.example.talabatiapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    // Database Name
    static final String DATABASE = "Talabati.db";
    // Database Version
    static final int DB_VERSION = 1;
    // Table Name
    static final String TABLE = "Product";
    // Table Field Name
    static final String P_ID = "id";
    static final String P_NAME = "name";
    static final String P_PRICE = "price";
    static final String P_QUANTITY = "quantity";
    static final String P_IMAGE="image";
    static final String P_CATEGORYID="c_id";
    Context context;

    public DBHelper(Context context) {
        super(context, DATABASE, null, DB_VERSION);
        this.context = context;
        //   Toast.makeText(context, " here constructor dbhelper"   , Toast.LENGTH_LONG).show();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + TABLE + " ( " + P_ID
                + " INTEGER PRIMARY KEY, " + P_NAME + " text, "
                + P_PRICE + " text, " + P_QUANTITY + " text, " + P_CATEGORYID   + " text, " +P_IMAGE  + " text )";
        System.out.println(" ----- create query : " + createQuery);
        // Toast.makeText(context, " here createQuery" + createQuery   , Toast.LENGTH_LONG).show();
// Create StudentInfo table using SQL query
        db.execSQL(createQuery);
        fillProducts();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println(" ----- upgrade query : ");
        //Toast.makeText(context, " here  upgrade query : "     , Toast.LENGTH_LONG).show();
// Drop old version table
        db.execSQL("Drop table " + TABLE);
// Create New Version table
        onCreate(db);
    }

    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(P_ID,product.getProductId());
        values.put(P_NAME, product.getProductName());
        values.put(P_PRICE, product.getPrice());
        values.put(P_QUANTITY, product.getQuantity());
        values.put(P_CATEGORYID,product.getCategory_id());
        values.put(P_IMAGE,product.getImage());
        db.insert(TABLE, null, values);
        db.close();
    }
    public List<Product> getProducts(int categoryId) {
        List<Product> productList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE+ " WHERE " + P_CATEGORYID+ "=" +categoryId;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id =cursor.getInt(0);
                String name =cursor.getString(1);
                double price = Double.parseDouble(cursor.getString(2));
                int quantity = Integer.parseInt(cursor.getString(3));
                int category_id = Integer.parseInt(cursor.getString(4));
                int image = Integer.parseInt(cursor.getString(5));
                productList.add(new Product(id,name,price,quantity,category_id,image));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return productList;
    }
    public void fillProducts()
    {
        this.addProduct(new Product(1,"pipe cleaner",4,6,1,R.drawable.a));
        this.addProduct(new Product(2,"pipe cleaner",4,6,1,R.drawable.a));
        this.addProduct(new Product(3,"pipe cleaner",4,6,1,R.drawable.a));
        this.addProduct(new Product(4,"pipe cleaner",4,6,1,R.drawable.a));
        this.addProduct(new Product(5,"pipe cleaner",4,6,1,R.drawable.a));
        this.addProduct(new Product(6,"pipe cleaner",4,6,1,R.drawable.a));
        this.addProduct(new Product(7,"pipe cleaner",4,6,1,R.drawable.a));
        this.addProduct(new Product(8,"pipe cleaner",4,6,1,R.drawable.a));
        this.addProduct(new Product(9,"pipe cleaner",4,6,1,R.drawable.a));
        this.addProduct(new Product(10,"pipe cleaner",4,6,1,R.drawable.a));
        this.addProduct(new Product(11,"pipe cleaner",4,6,1,R.drawable.a));
        this.addProduct(new Product(12,"pipe cleaner",4,6,1,R.drawable.a));
        this.addProduct(new Product(13,"pipe cleaner",4,6,1,R.drawable.a));
        this.addProduct(new Product(14,"pipe cleaner",4,6,1,R.drawable.a));

        this.addProduct(new Product(15,"cucumber",2,6,2,R.drawable.a));
        this.addProduct(new Product(16,"cucumber",2,6,2,R.drawable.a));
        this.addProduct(new Product(17,"cucumber",2,6,2,R.drawable.a));
        this.addProduct(new Product(18,"cucumber",2,6,2,R.drawable.a));
        this.addProduct(new Product(19,"cucumber",2,6,2,R.drawable.a));
        this.addProduct(new Product(20,"cucumber",2,6,2,R.drawable.a));

        this.addProduct(new Product(21,"apple",2,6,3,R.drawable.a));
        this.addProduct(new Product(22,"apple",2,6,3,R.drawable.a));
        this.addProduct(new Product(23,"apple",2,6,3,R.drawable.a));
        this.addProduct(new Product(24,"apple",2,6,3,R.drawable.a));
        this.addProduct(new Product(25,"apple",2,6,3,R.drawable.a));
        this.addProduct(new Product(26,"apple",2,6,3,R.drawable.a));

        this.addProduct(new Product(27,"beaf",2,6,4,R.drawable.a));
        this.addProduct(new Product(28,"beaf",2,6,4,R.drawable.a));
        this.addProduct(new Product(29,"beaf",2,6,4,R.drawable.a));
        this.addProduct(new Product(30,"beaf",2,6,4,R.drawable.a));
        this.addProduct(new Product(31,"beaf",2,6,4,R.drawable.a));
        this.addProduct(new Product(32,"beaf",2,6,4,R.drawable.a));

        this.addProduct(new Product(33,"egg",2,6,5,R.drawable.a));
        this.addProduct(new Product(34,"egg",2,6,5,R.drawable.a));
        this.addProduct(new Product(35,"egg",2,6,5,R.drawable.a));
        this.addProduct(new Product(36,"egg",2,6,5,R.drawable.a));
        this.addProduct(new Product(37,"egg",2,6,5,R.drawable.a));
        this.addProduct(new Product(38,"egg",2,6,5,R.drawable.a));
        this.addProduct(new Product(39,"egg",2,6,5,R.drawable.a));
        this.addProduct(new Product(40,"egg",2,6,5,R.drawable.a));
    }
}