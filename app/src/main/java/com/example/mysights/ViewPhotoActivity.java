package com.example.mysights;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mysights.model.Photo;

public class ViewPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_photo);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            if (intent != null) {
                Photo photo = (Photo) intent.getSerializableExtra("photo");
                TextView textView = findViewById(R.id.titleView);
                textView.setText(photo.getName());
                ImageView imageView = findViewById(R.id.imageView);
                Bitmap bmp = BitmapFactory.decodeByteArray(photo.getData(), 0, photo.
                        getData().length);
                imageView.setImageBitmap(bmp);
                TextView descriptionView = findViewById(R.id.descriptionView);
                descriptionView.setText(photo.getDescription());

            }
        }

    }
}