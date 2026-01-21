package com.example.talabatiapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    ImageView productImage;
    TextView productDescription;
    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        productImage= itemView.findViewById(R.id.productImage);
        productDescription =itemView.findViewById(R.id.productDescribtion);
    }
}
