package com.alissar.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;
    public ProductAdapter(List<Product> productList) {
        this.productList=productList;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
     return new ProductViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.Product_name.setText(product.getName());
        holder.Product_details.setText("Price: $" + product.getPrice() + " | Qty: " + product.getQuantity());
    }

    @Override
    public int getItemCount() {
       return productList.size();
    }
         public static class ProductViewHolder extends RecyclerView.ViewHolder {
             TextView Product_name,Product_details;

            public ProductViewHolder(View itemView) {
                super(itemView);
                Product_name = itemView.findViewById(R.id.product_name_field);
                Product_details = itemView.findViewById(R.id.text_details);
            }
        }
 }


