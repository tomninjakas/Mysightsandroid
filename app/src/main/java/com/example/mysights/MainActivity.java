package com.example.mysights;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import com.example.mysights.model.Photo;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    private String filterSelection = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.photoRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        ImageView imageView = findViewById(R.id.cityPhotoView);

        String filterSelection = "";
        if (savedInstanceState==null) {
            Intent intent = getIntent();
            filterSelection = intent.getStringExtra("filter");
        } else {
            filterSelection = savedInstanceState.getString("filter");
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        MainViewModel model = new ViewModelProvider(this).get(MainViewModel.class);
        ImageView imageView = findViewById(R.id.cityPhotoView);

        model.getPhotos(filterSelection).observe(this, photos -> {
            PhotoRecyclerAdapter photoRecyclerAdapter = new PhotoRecyclerAdapter(
                    photos, imageView);
            RecyclerView recyclerView = findViewById(R.id.photoRecycler);
            recyclerView.setAdapter(photoRecyclerAdapter);
        });
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.filterSelection =  savedInstanceState.getString("filter");
        Log.d(TAG, "onRestoreInstanceState");
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable("filter",this.filterSelection);
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");

    }

    public void onCreateButton(View view) {
        Intent intent = new Intent(this,CreateActivity.class);
        intent.putExtra("type",filterSelection);
        startActivity(intent);
    }
}