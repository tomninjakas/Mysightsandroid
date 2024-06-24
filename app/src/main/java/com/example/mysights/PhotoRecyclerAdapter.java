package com.example.mysights;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.mysights.model.Photo;

public class PhotoRecyclerAdapter extends
        RecyclerView.Adapter<PhotoViewHolder> {
    private final ImageView imageView;
    List<Photo> photoList;
    private Context context;

    public PhotoRecyclerAdapter(List<Photo> photoList, ImageView imageView) {
        this.photoList = photoList;
        this.imageView = imageView;
        Photo photo = photoList.get(0);
        Bitmap bmp = BitmapFactory.decodeByteArray(photo.getData(), 0, photo.
                getData().length);
        imageView.setImageBitmap(bmp);
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(context,ViewPhotoActivity.class);
            intent.putExtra("photo", photoList.get(0));
            context.startActivity(intent);
        });
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    @NonNull
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View photoView = inflater.inflate(R.layout.photo_recycler_item, parent, false);

        // Return a new holder instance
        return  new PhotoViewHolder(photoView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        // Get the data model based on position
        Photo photo = photoList.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.photoDescription;
        textView.setText(photo.getName());
        textView.setOnClickListener(view ->
        {
            Bitmap bmp = BitmapFactory.decodeByteArray(photo.getData(), 0, photo.
                    getData().length);
            imageView.setImageBitmap(bmp);
            imageView.setOnClickListener(v -> {
                Intent intent = new Intent(context,ViewPhotoActivity.class);
                intent.putExtra("photo", photo);
                context.startActivity(intent);
            });
        });

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return photoList.size();
    }

}

