package com.poly.lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.poly.lab4.adapter.CategoryAdapter;
import com.poly.lab4.listener.OnClick;
import com.poly.lab4.modelCategory.Category;
import com.poly.lab4.modelPost.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnClick {
    private List<Category> categoryList;
    private RecyclerView rvCategory;
    private CategoryAdapter categoryAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvCategory = findViewById(R.id.rvCategory);
        categoryList = new ArrayList<>();
        progressBar = findViewById(R.id.process);
        categoryAdapter = new CategoryAdapter(categoryList, this);
        rvCategory.setAdapter(categoryAdapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvCategory.setLayoutManager(manager);
getData();

    }


    private void getData() {
        PolyRetrofit.getInstanse().getCategories("1", "100").enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.code() == 200) {
                    categoryList.addAll(response.body());
                    categoryAdapter.changeDataset(categoryList);
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("erorr", t.getMessage());

            }
        });
    }

    @Override
    public void onClick(int pos) {
        Intent intent = new Intent(MainActivity.this,PostActivity.class);
        intent.putExtra("id",categoryList.get(pos).getId()+"");
        startActivity(intent);
    }
}
