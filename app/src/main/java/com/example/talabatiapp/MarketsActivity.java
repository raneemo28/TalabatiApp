package com.example.talabatiapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MarketsActivity extends AppCompatActivity {

    DBHelper dbhelper = new DBHelper(this);
    List<Market> marketList = new ArrayList<>();
    //Market market2;
    //Market market1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            RecyclerView recyclerView = findViewById(R.id.RecyclerView);
            // dbhelper = new DBHelper(this);
            marketList = dbhelper.getMarkets();
            // Instance 1: Sunrise Organics
             /*market1 = new Market(
                    11,
                    "Sunrise Organics",
                    "Al Shaalan",
                    LocalTime.of(7, 30), // Opens at 07:30 AM
                    LocalTime.of(20, 0),  // Closes at 08:00 PM
                    987654,
                    R.drawable.market_one
            );

// Instance 2: Urban Pantry
             market2 = new Market(
                    12,
                    "Urban Pantry",
                    "Al Mazzeh",
                    LocalTime.of(10, 0), // Opens at 10:00 AM
                    LocalTime.of(23, 30), // Closes at 11:30 PM
                    456789,
                    R.drawable.market_one
            );
            marketList.add(market1);
            marketList.add(market2);
*/
            if (marketList == null) {
                marketList = new ArrayList<>();
            }
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            MarketAdapter adapter = new MarketAdapter(marketList, this);
            recyclerView.setAdapter(adapter);
            return insets;
        });
       /* RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        MarketAdapter adapter = new MarketAdapter(marketList, this);
        recyclerView.setAdapter(adapter);*/
    }
}