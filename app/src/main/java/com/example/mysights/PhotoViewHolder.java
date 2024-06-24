package com.example.mysights;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PhotoViewHolder extends RecyclerView.ViewHolder {
    TextView photoDescription;
    public PhotoViewHolder(View itemView) {
        super(itemView);
        photoDescription = itemView.findViewById(R.id.photoRecyclerId);

    }
}
