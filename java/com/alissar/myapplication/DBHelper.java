package com.alissar.myapplication;

//package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//import model.StudentInfo;
/**
 * Created by user on 5/4/2023.
 */

public class DBHelper extends SQLiteOpenHelper {
    // Database Name
    static final String DATABASE = "Products.db";
    // Database Version
    static final int DB_VERSION = 6;
    // Table Name
    static final String TABLE = "Products";
    // Table Field Name
    //static final String S_ID = "_id";
    static final String P_ID = "id";
    static final String P_NAME = "name";
    static final String P_PRICE = "price";
    static  final String P_QUANTITY="quantity";
    Context context ;
    // Override constructor
    public DBHelper(Context context) {
        super(context, DATABASE, null, DB_VERSION);
        this.context = context;
        //Toast.makeText(context, " here constructor db helper"   , Toast.LENGTH_LONG).show();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + TABLE + " ( " + P_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + P_NAME + " text, "
                +  P_PRICE  + " text, " + P_QUANTITY + " text )";
        System.out.println(" ----- create query : " + createQuery);
        // Toast.makeText(context, " here createQuery" + createQuery   , Toast.LENGTH_LONG).show();
// Create StudentInfo table using SQL query
        db.execSQL(createQuery);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println(" ----- upgrade query : " );
        //Toast.makeText(context, " here  upgrade query : "     , Toast.LENGTH_LONG).show();
// Drop old version table
        db.execSQL("Drop table " + TABLE);
// Create New Version table
        onCreate(db);
    }

    public void addProductDetails(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(P_ID, product.getId());
        values.put(P_NAME, product.getName());
        values.put(P_PRICE, product.getPrice());
        values.put(P_QUANTITY,product.getQuantity());
// Inserting Row
        db.insert(TABLE, null, values);
        db.close();
    }

    public int updateProductDetails(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(P_ID, product.getId());
        values.put(P_NAME, product.getName());
        values.put(P_PRICE, product.getPrice());
        values.put(P_QUANTITY,product.getQuantity());
// updating row
        int result  = db.update(TABLE, values, P_ID + " = ?",
                new String[]{String.valueOf(product.getId())});
        //@return the number of rows affected

        db.close();
        return result;

    }
    public int deleteProductDetails(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE, P_ID + " = ?",
                new String[]{String.valueOf(product.getId())});
        db.close();
        //@return the number of rows affected
        return result;
    }
    public List<Product> getAllProducts() {
        List<Product> ProductsList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(cursor.getInt(0));
                product.setName(cursor.getString(1));
                product.setPrice(cursor.getDouble(2));
                product.setQuantity(cursor.getInt(3));
              // Adding student information to list
              //  StudentList.add(studentInfo);
                ProductsList.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ProductsList;
    }

}
