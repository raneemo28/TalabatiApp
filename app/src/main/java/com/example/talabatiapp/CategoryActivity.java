package com.example.talabatiapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    DBHelper DB = new DBHelper(this);
    List<Category> categories;
    RecyclerView CategoriesRecycleView;
    // In CategoryActivity.java
// Replace the onCreate method with this corrected version:

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        categories = new ArrayList<>();
        DB.fillCategories();
        // Get the market_id passed from MarketsActivity
        int receivedMarketId = getIntent().getIntExtra("MARKET_ID", -1);

        // Get categories for this specific market
        if (receivedMarketId != -1) {
            categories = DB.getCategoryDetailsBasedOnMarket(receivedMarketId);
        } else {
            // If no market_id was passed, show all categories (fallback)
            categories = DB.getCategoryDetails();
        }

        CategoriesRecycleView = findViewById(R.id.RecyclerView);
        GridLayoutManager CategoriesGrid = new GridLayoutManager(this, 2);
        CategoriesRecycleView.setLayoutManager(CategoriesGrid);

        CategoryAdapter CategoryAdapter = new CategoryAdapter(this, categories);
        CategoriesRecycleView.setAdapter(CategoryAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

       /* categories = new ArrayList<>();

        // Get the market_id passed from MarketsActivity
        int receivedMarketId = getIntent().getIntExtra("MARKET_ID", -1);

        // Get categories for this specific market
        if (receivedMarketId != -1) {
            categories = DB.getCategoryDetailsBasedOnMarket(receivedMarketId);
        } else {
            // If no market_id was passed, show all categories (fallback)
            categories = DB.getCategoryDetails();
        }

        CategoriesRecycleView = findViewById(R.id.RecyclerView);
        GridLayoutManager CategoriesGrid = new GridLayoutManager(this, 2);
        CategoriesRecycleView.setLayoutManager(CategoriesGrid);

        CategoryAdapter CategoryAdapter = new CategoryAdapter(this, categories);
        CategoriesRecycleView.setAdapter(CategoryAdapter);*/
    }
}