package com.example.mysights.client;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;

import com.example.mysights.CreateViewModel;
import com.example.mysights.model.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class PhotoApiClient {
    private static final String BASE_URL = "http://10.0.2.2:9000";
    private final PhotoApi photoApi;

    public PhotoApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(
                        new ObjectMapper()))
                .build();

        photoApi = retrofit.create(PhotoApi.class);
    }


    public MutableLiveData<List<Photo>> getAllPhotos() {
        MutableLiveData<List<Photo>> photoList = new MutableLiveData<>();

        Call<List<Photo>> call = photoApi.getAllPhotos();

        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.body() != null) {
                    photoList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.e("REST Client",
                        "Tried to get all photos{}. Got back " + t.getLocalizedMessage());
                photoList.postValue(Collections.emptyList());
            }
        });
        return photoList;
    }
    public MutableLiveData<List<Photo>> getFilteredPhotos(String filterSelection) {
        MutableLiveData<List<Photo>> photoList = new MutableLiveData<>();

        Call<List<Photo>> call = photoApi.findByType(filterSelection);

        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.body() != null) {
                    photoList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.e("REST Client",
                        "Tried to get all photos{}. Got back " + t.getLocalizedMessage());
                photoList.postValue(Collections.emptyList());
            }
        });
        return photoList;
    }

    public void addPhoto(CreateViewModel viewModel, Photo photo) {

        Call<Photo> call = photoApi.addPhoto(photo);

        call.enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call,
                                   Response<Photo> response) {
                if (response.body() != null) {
                    viewModel.updateFromRest(photo);
                }
            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {
                Log.e("REST Client",
                        "Tried to get all photos{}. Got back " + t.getLocalizedMessage());
                viewModel.FailedFromRest(t.getLocalizedMessage());
            }
        });
        return;
    }

}
