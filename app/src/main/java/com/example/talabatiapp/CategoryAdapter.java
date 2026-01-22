package com.example.talabatiapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {


    Context context;
    List<Category> categories;
    public CategoryAdapter( Context context,List<Category> categories)
    {
        this.categories=categories;
        this.context=context;
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category Category = categories.get(position);
        holder.CategoryText.setText(Category.GetCategoryName());
        holder.CategoryImage.setImageResource(Category.getCategoryImageResource());
        holder.CategoryImage.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent ProductIntent = new Intent(context,ProductActivity.class);
                ProductIntent.putExtra("CATEGORY_ID",Category.GetCategoryId());
                context.startActivity(ProductIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView CategoryImage;
        TextView CategoryText;


        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            CategoryImage = itemView.findViewById(R.id.Category_image);
            CategoryText = itemView.findViewById(R.id.Category_text);
        }

    }
}

