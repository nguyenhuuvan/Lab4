package com.poly.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.poly.lab4.adapter.MediaAdater;
import com.poly.lab4.modelMedia.Media;
import com.poly.lab4.modelPost.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MediaActivity extends AppCompatActivity {
    GridView grid;
    List<Media> mediaList;
    MediaAdater adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        progressBar = findViewById(R.id.process);
        mediaList = new ArrayList<>();
        adapter = new MediaAdater(this, mediaList);
        grid = (GridView) findViewById(R.id.grid);
        grid.setAdapter(adapter);

        try {
            getData();
        } catch (Exception e) {
            getData2();
        }
    }

    private void getData2() {
        PolyRetrofit.getInstanse().getMediaofPost("377").enqueue(new Callback<List<Media>>() {
            @Override
            public void onResponse(Call<List<Media>> call, Response<List<Media>> response) {
                progressBar.setVisibility(View.GONE);
                mediaList.addAll(response.body());
                adapter.changeDataset(mediaList);
            }

            @Override
            public void onFailure(Call<List<Media>> call, Throwable t) {

            }
        });
    }

    private void getData() {
        PolyRetrofit.getInstanse().getMediaofPost(getIntent().getStringExtra("id")).enqueue(new Callback<List<Media>>() {
            @Override
            public void onResponse(Call<List<Media>> call, Response<List<Media>> response) {
                progressBar.setVisibility(View.GONE);
                mediaList.addAll(response.body());
                adapter.changeDataset(mediaList);
            }

            @Override
            public void onFailure(Call<List<Media>> call, Throwable t) {

            }
        });
    }
}
