package com.example.mysights.client;

import java.util.List;

import com.example.mysights.model.Photo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PhotoApi {
    @GET("/photos")
    Call<List<Photo>> getAllPhotos();

    @POST("/photos")
    Call<Photo> addPhoto(@Body Photo photo);

    @GET("/photos/byType/{type}")
    Call<List<Photo>> findByType(@Path("type") String name);
}

