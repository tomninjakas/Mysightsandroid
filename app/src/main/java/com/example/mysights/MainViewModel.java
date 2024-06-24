package com.example.mysights;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import com.example.mysights.client.PhotoApiClient;
import com.example.mysights.model.Photo;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<Photo>> photos;
    private PhotoApiClient photoApiClient  = new PhotoApiClient();


    public LiveData<List<Photo>> getPhotos(String filterSelection) {
        if (photos == null) {
            photos = new MutableLiveData<>();
            loadPhotos(filterSelection);
        }
        return photos;
    }

    private void loadPhotos(String filterSelection) {
        photos = photoApiClient.getFilteredPhotos(filterSelection);
    }
}

