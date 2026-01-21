package com.example.talabatiapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DBHelperMarkets extends SQLiteOpenHelper {
    // Database Name
    static final String DATABASE = "Talabati.db";
    // Database Version
    static final int DB_VERSION = 5;
    // Table Name
    static final String TABLE = "Market";
    // Table Field Name
    static final String M_ID = "id";
    static final String M_NAME = "name";
    static final String M_LOCATION = "location";
    static final String M_OPENINGTIME = "opening_time";
    static final String M_CLOSINGTIME = "closing_time";
    static final String M_PHONENUMBER = "phone_number";
    static final String M_IMAGE = "image";

    Context context;

    public DBHelperMarkets(Context context) {
        super(context, DATABASE, null, DB_VERSION);
        this.context = context;
        //   Toast.makeText(context, " here constructor dbhelper"   , Toast.LENGTH_LONG).show();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + TABLE + " ( " + M_ID
                + " INTEGER PRIMARY KEY, " + M_NAME + " text, "
                + M_LOCATION + " text, " + M_OPENINGTIME + " text, " + M_CLOSINGTIME + " text, "
                + M_PHONENUMBER + " text, " + M_IMAGE + " text )";
        System.out.println(" ----- create query : " + createQuery);
        // Toast.makeText(context, " here createQuery" + createQuery   , Toast.LENGTH_LONG).show();
// Create StudentInfo table using SQL query
        db.execSQL(createQuery);
        fillMarkets(db);
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

    public void addMarket(SQLiteDatabase db, Market market) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(M_ID, market.getMarketId());
        values.put(M_NAME, market.getMarketName());
        values.put(M_LOCATION, market.getMarketLocation());
        values.put(M_OPENINGTIME, market.getMarketOpeningTime().toString());
        values.put(M_CLOSINGTIME, market.getMarketClosingTime().toString());
        values.put(M_PHONENUMBER, market.getMarketPhoneNum());
        values.put(M_IMAGE, market.getMarketImageResource());
        db.insert(TABLE, null, values);
        //db.close();
    }

    public List<Market> getMarkets() {
        List<Market> marketList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String location = cursor.getString(2);
                LocalTime open = LocalTime.parse(cursor.getString(3));
                LocalTime close = LocalTime.parse(cursor.getString(4));
                int phone = Integer.parseInt(cursor.getString(5));
                int image = Integer.parseInt(cursor.getString(6));
                marketList.add(new Market(id, name, location, open, close, phone, image));
            } while (cursor.moveToNext());
        }
        cursor.close();
        //db.close();
        return marketList;
    }

    public void fillMarkets(SQLiteDatabase db) {
        addMarket(db, new Market(1, "Fresh Market","Al Mazzeh",
                LocalTime.of(8, 0), LocalTime.of(16, 0),123456,R.drawable.market_one));

        addMarket(db, new Market(2, "Al_Khal Market","AL Abed Street",
                LocalTime.of(8, 0), LocalTime.of(18, 30),123456,R.drawable.market_one));

        /*addMarket(db, new Market(3, "Al_Fardous Market","Al Shaalan",
                LocalTime.of(9, 0), LocalTime.of(16, 0),123456,R.drawable.market_one));

        addMarket(db, new Market(4, "Al Nour Market","Al Mazzeh",
                LocalTime.of(11, 0), LocalTime.of(16, 0),123456,R.drawable.market_one));

        addMarket(db, new Market(5, "The Green Corner Market","AL Abed Street",
                LocalTime.of(12, 0), LocalTime.of(16, 0),123456,R.drawable.market_one));

        addMarket(db, new Market(6, "AL Hamra Market","Al Hamra",
                LocalTime.of(10, 30), LocalTime.of(16, 0),123456,R.drawable.market_one));

        addMarket(db, new Market(7, "Al Mazzeh Market","Al Mazzeh",
                LocalTime.of(8, 0), LocalTime.of(23, 30),123456,R.drawable.market_one));

        addMarket(db, new Market(8, "Al Shaalan Market","Al Shaalan",
                LocalTime.of(8, 0), LocalTime.of(16, 0),123456,R.drawable.market_one));

        addMarket(db, new Market(9, "AL Hayat Market","Al Hamra",
                LocalTime.of(8, 0), LocalTime.of(16, 0),123456,R.drawable.market_one));

        addMarket(db, new Market(10, "Noura Market","Al Hamra",
                LocalTime.of(11, 30), LocalTime.of(16, 0),123456,R.drawable.market_one));*/

    }
}
