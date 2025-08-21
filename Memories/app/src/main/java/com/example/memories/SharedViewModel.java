package com.example.memories;

public class SharedViewModel {
    private String title;
    private String description;
    private String location;
    private byte[] image;

    public SharedViewModel(String title, String description, String location, byte[] image) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.image = image;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public byte[] getImage() { return image; }
}


//public class SharedViewModel extends ViewModel {
//    private final MutableLiveData<ArrayList<MemoryItem>> memoryList = new MutableLiveData<>(new ArrayList<>());
//
//    public LiveData<ArrayList<MemoryItem>> getMemoryList() {
//        return memoryList;
//    }
//
//    public void addMemory(MemoryItem item) {
//        ArrayList<MemoryItem> currentList = memoryList.getValue();
//        if (currentList != null) {
//            currentList.add(item);
//            memoryList.setValue(currentList);
//        }
//    }
//
//    public void setMemoryList(ArrayList<MemoryItem> list) {
//        memoryList.setValue(list);
//    }
//}

