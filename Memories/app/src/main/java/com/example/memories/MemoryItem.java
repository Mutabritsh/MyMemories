package com.example.memories;

//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//
//import java.util.ArrayList;
//
//public class ViewModel extends androidx.lifecycle.ViewModel {
//
//    private final MutableLiveData<ArrayList<String>> dataList = new MutableLiveData<>(new ArrayList<>());
//
//    public LiveData<ArrayList<String>> getDataList() {
//        return dataList;
//    }
//
//    public void addData(String data) {
//        ArrayList<String> currentList = dataList.getValue();
//        if (currentList != null) {
//            currentList.add(data);
//            dataList.setValue(currentList); // notify observers
//        }
//    }
//
//    public void setDataList(ArrayList<String> list) {
//        dataList.setValue(list); // use this to load from database initially
//    }
//}
public class MemoryItem {
    private int id;
    private String title;
    private String description;
    private String location;
    private String image; // Can be a path, Uri, or Base64 string

    public MemoryItem(int id, String title, String description, String location, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.image = image;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public String getImage() { return image; }
}