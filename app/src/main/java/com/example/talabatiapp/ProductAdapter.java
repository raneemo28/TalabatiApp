package com.example.talabatiapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    Context context;
    List<Product> products;
    TextView totalBar;
    Order order;
    public ProductAdapter(Context context,List<Product> products, TextView totalBar)
    {
        this.context=context;
        this.products=products;
        this.order=new Order(1,0);
        this.totalBar=totalBar;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.product_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String describtion=products.get(position).getProductName()+"\n$"+products.get(position).getPrice()+"\n"
                +"q: "+products.get(position).getQuantity()+"peices";
        holder.productDescription.setText(describtion);
        holder.productImage.setImageResource(products.get(position).getImage());
        holder.productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double price=products.get(position).getPrice();
                if(products.get(position).isAvailabile()) {
                    order.setTotal(order.getTotal() + price);
                    products.get(position).setQuantity(products.get(position).getQuantity()-1);
                    totalBar.setText("total: $" + order.getTotal());
                    String describtion=products.get(position).getProductName()+"\n$"+products.get(position).getPrice()+"\n"
                            +"q: "+products.get(position).getQuantity()+"peices";
                    holder.productDescription.setText(describtion);
                    Toast.makeText(v.getContext(), "product is added", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(v.getContext(), "product is not available", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
