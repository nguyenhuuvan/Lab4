package com.poly.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.poly.lab4.adapter.PostAdapter;
import com.poly.lab4.modelPost.Content;
import com.poly.lab4.modelPost.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {
    GridView grid;
    List<Post> postList;
    PostAdapter adapter;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        progressBar = findViewById(R.id.process);
        postList = new ArrayList<>();
        adapter = new PostAdapter(this, postList);
        grid = (GridView) findViewById(R.id.grid);
        grid.setAdapter(adapter);

        getData();

    }

    private void getData() {
        PolyRetrofit.getInstanse().getPostOfCategory(getIntent().getStringExtra("id"), "100", "1").enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                progressBar.setVisibility(View.GONE);
                postList = response.body();
                adapter.changeDataset(postList);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("error", t.getMessage() + "");
            }
        });
    }
}
