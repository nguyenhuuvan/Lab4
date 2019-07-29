package com.poly.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageDetails extends AppCompatActivity {
    private ImageView imgDetails;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        imgDetails = (ImageView) findViewById(R.id.imgDetails);


//        Glide
//                .with(this)
//                .load(getIntent().getStringExtra("link"))
//                .centerCrop()
//                .into(imgDetails);
    }
}
