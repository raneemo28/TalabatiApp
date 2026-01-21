package com.example.talabatiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class ProductActivity extends AppCompatActivity {
    DBHelper dbHelper = new DBHelper(this);
    List<Product> products= new ArrayList<>();
    TextView totalbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_prcoduct);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            RecyclerView productsGrid = findViewById(R.id.RecyclerView);
            //fillProducts(dbHelper);
            int receivedCategoryId = getIntent().getIntExtra("CATEGORY_ID", -1);
            if (receivedCategoryId != -1) {
                products=dbHelper.getProducts(receivedCategoryId);
                GridLayoutManager grid =new GridLayoutManager(this,2);
                productsGrid.setLayoutManager(grid);
                totalbar=findViewById(R.id.totalBar);
                productsGrid.setAdapter(new ProductAdapter(getApplicationContext(),products,totalbar));
            }
            else{
                Toast.makeText(this,"can not load data",Toast.LENGTH_SHORT).show();
            }
            return insets;
        });
    }

}