package com.example.mysights;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mysights.client.PhotoApiClient;
import com.example.mysights.model.Photo;


public class CreateViewModel extends ViewModel {
    private MutableLiveData<Photo> photoMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> errorMessages = new MutableLiveData<>();

    private PhotoApiClient photoApiClient  = new PhotoApiClient();
    public LiveData<Photo> getPhoto() {
        return photoMutableLiveData;
    }
    public void setPhoto(Photo photo) {
        photoApiClient.addPhoto(this,photo);
    }

    public void updateFromRest(Photo photo) {
        photoMutableLiveData.postValue(photo);
    }

    public void FailedFromRest(String localizedMessage) {
        errorMessages.postValue(localizedMessage);
    }

    public LiveData<String> errorMessages() {
        return errorMessages;
    }
}
