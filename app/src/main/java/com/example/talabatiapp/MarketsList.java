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

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MarketsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_market_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RecyclerView recyclerView = findViewById(R.id.marketRecyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        List<Market> marketList = new ArrayList<>();

        marketList.add(new Market(1, "Fresh Market", R.drawable.market_one,
                "Downtown", 123456, LocalTime.of(8, 0), LocalTime.of(22, 0)));

        marketList.add(new Market(2, "City Corner",R.drawable.market_one,
                "West Side", 654321, LocalTime.of(9, 0), LocalTime.of(18, 0)));

        marketList.add(new Market(3, "Green Grocery", R.drawable.market_one,
                "East Gate", 999888, LocalTime.of(7, 30), LocalTime.of(21, 0)));

        MarketAdapter adapter = new MarketAdapter(marketList, this);
        recyclerView.setAdapter(adapter);
    }
}