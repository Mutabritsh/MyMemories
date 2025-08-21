package com.example.memories;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class AddFragment extends Fragment {
    Button chooseImageButton, getlocationButton, saveButton;
    EditText title, description;
    ImageView memoryImageView;
    TextView locationTextView;
    private LocationViewModel viewModel;

    private ActivityResultLauncher<Intent> resultLauncher;
    private void showAddressFromLocation(Location location) {
        Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());

        try {
            List<Address> addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    1 // just the first match
            );

            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                String addressString = address.getAddressLine(0); // full address
                locationTextView.setText(addressString);
            } else {
                locationTextView.setText("Address not found");
            }

        } catch (IOException e) {
            e.printStackTrace();
            locationTextView.setText("Unable to get address");
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);
        chooseImageButton = view.findViewById(R.id.chooseImageButton);
        getlocationButton = view.findViewById(R.id.getlocationButton);
        saveButton = view.findViewById(R.id.savememoryButton);
        memoryImageView = view.findViewById(R.id.memoryImageView);
        locationTextView = view.findViewById(R.id.location);

        viewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);

        getlocationButton.setOnClickListener(v -> {
            Location location = viewModel.getLocation().getValue();
            if (location != null) {
                locationTextView.setText("Lat: " + location.getLatitude() + "\nLng: " + location.getLongitude());
            } else {
                locationTextView.setText("Location not available");
            }
        });

        getlocationButton.setOnClickListener(v -> {
            Location location = viewModel.getLocation().getValue();
            if (location != null) {
                showAddressFromLocation(location);
            } else {
                locationTextView.setText("Location not available");
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getTitle = title.getText().toString();
                String getDescription = description.getText().toString();

            }
        });



        chooseImageButton.setOnClickListener(v -> pickImage());

       
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try {
                            Uri imageUri = result.getData().getData();
                            memoryImageView.setImageURI(imageUri);
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), "Failed to load image", Toast.LENGTH_SHORT).show();


                        }
                    }
                });


        return view;
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        resultLauncher.launch(intent);


        
    }
}

