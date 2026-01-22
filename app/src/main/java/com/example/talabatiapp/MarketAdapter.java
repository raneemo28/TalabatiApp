package com.example.talabatiapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MarketViewHolder> {
    private List<Market> marketList;
    private Context context;

    public MarketAdapter(List<Market> marketList, Context context) {
        this.marketList = marketList;
        this.context = context;
    }

    @NonNull
    @Override
    public MarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_market, parent, false);
        return new MarketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketViewHolder holder, int position) {
        Market market = marketList.get(position);

        holder.name.setText(market.getMarketName());
        holder.image.setImageResource(market.getMarketImageResource());

        // Check time and set status
        market.setMarketAvailable();
        if (market.isMarketAvailable()) {
            holder.status.setText("Open");
            holder.status.setTextColor(Color.GREEN);
        } else {
            holder.status.setText("Closed");
            holder.status.setTextColor(Color.RED);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CategoryActivity.class);
            intent.putExtra("MARKET_ID", market.getMarketId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() { return marketList.size(); }

    public static class MarketViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, status;
        public MarketViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.marketImage);
            name = itemView.findViewById(R.id.marketName);
            status = itemView.findViewById(R.id.marketStatus);
        }
    }
}