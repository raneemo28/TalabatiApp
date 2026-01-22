package com.example.talabatiapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    // Database Name
    static final String DATABASE = "Talabati.db";
    // Database Version
    static final int DB_VERSION = 5;
    // Table Name
    static final String P_TABLE = "Product";
    // Table Field Name
    static final String P_ID = "id";
    static final String P_NAME = "name";
    static final String P_PRICE = "price";
    static final String P_QUANTITY = "quantity";
    static final String P_IMAGE="image";
    static final String P_CATEGORYID="c_id";
    static final String C_TABLE = "Categories";
    // Table Field Name
    static final String Category_ID = "_id";
    static final String Category_NAME = "name";

    static final String Category_IMAGE = "image";
    static final String Category_MarketId ="marketid";
    static final String M_TABLE = "Market";
    // Table Field Name
    static final String M_ID = "id";
    static final String M_NAME = "name";
    static final String M_LOCATION = "location";
    static final String M_OPENINGTIME = "opening_time";
    static final String M_CLOSINGTIME = "closing_time";
    static final String M_PHONENUMBER = "phone_number";
    static final String M_IMAGE = "image";
    Context context;

    public DBHelper(Context context) {
        super(context, DATABASE, null, DB_VERSION);
        this.context = context;
        //   Toast.makeText(context, " here constructor dbhelper"   , Toast.LENGTH_LONG).show();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String createQuery1 = "CREATE TABLE IF NOT EXISTS " + P_TABLE + " ( " + P_ID
                    + " INTEGER PRIMARY KEY, " + P_NAME + " text, "
                    + P_PRICE + " text, " + P_QUANTITY + " text, " + P_CATEGORYID   + " text, " +P_IMAGE  + " text )";
            System.out.println(" ----- create query : " + createQuery1);

            String createQuery2 = "CREATE TABLE IF NOT EXISTS " + C_TABLE + " ( "
                    + Category_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Category_NAME + " TEXT, "
                    + Category_IMAGE + " TEXT, "
                    + Category_MarketId + " TEXT )";
            System.out.println(" ----- create query : " + createQuery2);

            String createQuery3 = "CREATE TABLE IF NOT EXISTS " + M_TABLE + " ( " + M_ID
                    + " INTEGER PRIMARY KEY, " + M_NAME + " text, "
                    + M_LOCATION + " text, " + M_OPENINGTIME + " text, " + M_CLOSINGTIME + " text, "
                    + M_PHONENUMBER + " text, " + M_IMAGE + " text )";
            System.out.println(" ----- create query : " + createQuery3);

            // Create tables
            db.execSQL(createQuery1);
            db.execSQL(createQuery2);
            db.execSQL(createQuery3);

            // Verify tables were created
            Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
            while (cursor.moveToNext()) {
                System.out.println("Table exists: " + cursor.getString(0));
            }
            cursor.close();

            // Now fill with initial data
            fillMarkets(db);
            fillProducts(db);
            // Don't call fillCategories() here - it will be called separately
        } catch (Exception e) {
            System.out.println("Error creating database: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println(" ----- upgrade query : ");
        //Toast.makeText(context, " here  upgrade query : "     , Toast.LENGTH_LONG).show();
// Drop old version table
        db.execSQL("Drop table " + C_TABLE);
        db.execSQL("Drop table " + P_TABLE);
        db.execSQL("Drop table " + M_TABLE);

// Create New Version table
        onCreate(db);
    }

    public void addProduct(SQLiteDatabase db, Product product) {
       // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(P_ID,product.getProductId());
        values.put(P_NAME, product.getProductName());
        values.put(P_PRICE, product.getPrice());
        values.put(P_QUANTITY, product.getQuantity());
        values.put(P_CATEGORYID,product.getCategory_id());
        values.put(P_IMAGE,product.getImage());
        db.insert(P_TABLE, null, values);
        //db.close();
    }
    public void addCategoryDetails( Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Category_ID, category.GetCategoryId());
        values.put(Category_NAME, category.GetCategoryName());
        values.put(Category_IMAGE, category.getCategoryImageResource());
        values.put(Category_MarketId,category.GetMarketId());
       // Inserting Row
        db.insert(C_TABLE, null, values);
        //db.close();
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
        db.insert(M_TABLE, null, values);
        //db.close();
    }

    public List<Product> getProducts(int categoryId) {
        List<Product> productList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + P_TABLE+ " WHERE " + P_CATEGORYID+ "=" +categoryId;
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
        //db.close();
        return productList;
    }
    public List<Category> getCategoryDetails() {
        List<Category> CategoryList = new ArrayList<>();
// Select All Query
        String selectQuery = "SELECT * FROM " + C_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setCategoryId(cursor.getInt(0));
                category.setCategoryName(cursor.getString(1));
                category.setCategoryImage(Integer.parseInt(cursor.getString(2)));
                category.setMarketId(Integer.parseInt(cursor.getString(3)));

// Adding student information to list
                CategoryList.add(category);
            } while (cursor.moveToNext());
        }
        cursor.close();
        //db.close();
        return CategoryList;
    }
    public List<Market> getMarkets() {
        List<Market> marketList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + M_TABLE;
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

    public List<Category> getCategoryDetailsBasedOnMarket(int Market_id) {
        List<Category> CategoryList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + C_TABLE + " WHERE " + Category_MarketId + " = ?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(Market_id)});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setCategoryId(cursor.getInt(0));
                category.setCategoryName(cursor.getString(1));
                category.setCategoryImage(cursor.getInt(2));
                category.setMarketId(cursor.getInt(3)); // FIXED: Added this line

                // Adding category to list
                CategoryList.add(category);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return CategoryList;
    }
    public void fillCategories() {
        SQLiteDatabase db = this.getWritableDatabase();
        // First check if categories already exist
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + C_TABLE, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();

        // Only insert if table is empty
        if (count == 0) {
            addCategoryDetails(new Category(1, "detergents", R.drawable.ic_launcher_background, 1));
            addCategoryDetails(new Category(2, "Vegetables", R.drawable.ic_launcher_background, 1));
            addCategoryDetails(new Category(3, "Fruits", R.drawable.ic_launcher_background, 2));
            addCategoryDetails(new Category(4, "Meat", R.drawable.ic_launcher_background, 2));
            addCategoryDetails(new Category(5, "Animal Products", R.drawable.ic_launcher_background, 1));
            System.out.println("Categories table populated with initial data");


        }
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
    public void fillProducts(SQLiteDatabase db)
    {
        addProduct(db, new Product(1,"pipe cleaner",4,6,1,R.drawable.a));
        addProduct(db, new Product(2,"pipe cleaner",4,6,1,R.drawable.a));
        addProduct(db, new Product(3,"pipe cleaner",4,6,1,R.drawable.a));
        addProduct(db, new Product(4,"pipe cleaner",4,6,1,R.drawable.a));
        addProduct(db, new Product(5,"pipe cleaner",4,6,1,R.drawable.a));
        addProduct(db, new Product(6,"pipe cleaner",4,6,1,R.drawable.a));

        addProduct(db, new Product(7,"cucumber",2,6,2,R.drawable.a));
        addProduct(db, new Product(8,"cucumber",2,6,2,R.drawable.a));
        addProduct(db, new Product(9,"cucumber",2,6,2,R.drawable.a));
        addProduct(db, new Product(10,"cucumber",2,6,2,R.drawable.a));
        addProduct(db, new Product(11,"cucumber",2,6,2,R.drawable.a));

        addProduct(db, new Product(13,"apple",2,6,3,R.drawable.a));
        addProduct(db, new Product(14,"apple",2,6,3,R.drawable.a));
        addProduct(db, new Product(15,"apple",2,6,3,R.drawable.a));
        addProduct(db, new Product(16,"apple",2,6,3,R.drawable.a));
        addProduct(db, new Product(17,"apple",2,6,3,R.drawable.a));
        addProduct(db, new Product(18,"apple",2,6,3,R.drawable.a));

        addProduct(db, new Product(19,"beaf",2,6,4,R.drawable.a));
        addProduct(db, new Product(20,"beaf",2,6,4,R.drawable.a));
        addProduct(db, new Product(21,"beaf",2,6,4,R.drawable.a));
        addProduct(db, new Product(22,"beaf",2,6,4,R.drawable.a));
        addProduct(db, new Product(23,"beaf",2,6,4,R.drawable.a));
        addProduct(db, new Product(24,"beaf",2,6,4,R.drawable.a));
        addProduct(db, new Product(27,"beaf",2,6,4,R.drawable.a));
        addProduct(db, new Product(28,"beaf",2,6,4,R.drawable.a));
        addProduct(db, new Product(29,"beaf",2,6,4,R.drawable.a));
        addProduct(db, new Product(30,"beaf",2,6,4,R.drawable.a));
        addProduct(db, new Product(31,"beaf",2,6,4,R.drawable.a));
        addProduct(db, new Product(32,"beaf",2,6,4,R.drawable.a));

        addProduct(db, new Product(33,"egg",2,6,5,R.drawable.a));
        addProduct(db, new Product(34,"egg",2,6,5,R.drawable.a));
        addProduct(db, new Product(35,"egg",2,6,5,R.drawable.a));
        addProduct(db, new Product(36,"egg",2,6,5,R.drawable.a));
        addProduct(db, new Product(37,"egg",2,6,5,R.drawable.a));
        addProduct(db, new Product(38,"egg",2,6,5,R.drawable.a));
        addProduct(db, new Product(39,"egg",2,6,5,R.drawable.a));
        addProduct(db, new Product(40,"egg",2,6,5,R.drawable.a));
    }
}