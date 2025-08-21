package com.example.memories;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    RecyclerView recyclerView;
    MemoryAdapter adapter;
    DatabaseHelper dbHelper;
    List<Memory> memoryList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dbHelper = new DatabaseHelper(getContext());
        memoryList = new ArrayList<>();

        loadData();

        adapter = new MemoryAdapter(memoryList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void loadData() {
        Cursor cursor = dbHelper.getAllData();
        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                String location = cursor.getString(cursor.getColumnIndexOrThrow("location"));
                byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow("image"));

                memoryList.add(new Memory(title, description, location, image));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
