package com.example.memories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.location.Location;

public class LocationViewModel extends ViewModel {

    private final MutableLiveData<Location> currentLocation = new MutableLiveData<>();

    public void setLocation(Location location) {
        currentLocation.setValue(location);
    }

    public LiveData<Location> getLocation() {
        return currentLocation;
    }
}
