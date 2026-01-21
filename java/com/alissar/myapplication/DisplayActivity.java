package com.alissar.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DisplayActivity extends AppCompatActivity {
    ProductAdapter productAdapter;
    RecyclerView productRecycleView;
    DBHelper DB = new DBHelper(this);;
    TextView DisplayedProducts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            productRecycleView = findViewById(R.id.recyclerView);
            productRecycleView.setLayoutManager(new LinearLayoutManager(this));

            // 2. Get data and set the Adapter
            List<Product> products = DB.getAllProducts();
            productAdapter = new ProductAdapter(products);
            productRecycleView.setAdapter(productAdapter);
            return insets;
        });
    }
}