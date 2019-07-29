package com.poly.lab4.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.lab4.R;
import com.poly.lab4.listener.OnClick;
import com.poly.lab4.modelCategory.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    private List<Category> categoryList;
    private  OnClick onClick;

    public CategoryAdapter(List<Category> categoryList, OnClick onClick) {
        this.categoryList = categoryList;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_category, parent, false);
        return new ViewHolder(itemView);
    }
    public void changeDataset(List<Category> items) {
        this.categoryList = items;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Category category = categoryList.get(position);
        holder.tvCategory.setText("Name: "+category.getName());
        holder.tvID.setText("ID: "+category.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    onClick.onClick(position);
                } catch (Exception e) {
                    Log.e("lỗi onclick","lỗi onclick");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (categoryList == null) return 0;
        return categoryList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView tvCategory,tvID;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvID = itemView.findViewById(R.id.tvID);

        }
    }
}
