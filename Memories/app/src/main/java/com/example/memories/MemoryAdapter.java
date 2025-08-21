package com.example.memories;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import com.example.memories.Memory;

public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.ViewHolder> {

    private List<Memory> memoryList;

    public MemoryAdapter(List<Memory> memoryList) {
        this.memoryList = memoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Memory memory = memoryList.get(position);
        holder.title.setText(memory.getTitle());
        holder.description.setText(memory.getDescription());
        holder.location.setText(memory.getLocation());

        // Convert byte[] to Bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(memory.getImage(), 0, memory.getImage().length);
        holder.image.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return memoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, location;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.memoryTitle);
            description = itemView.findViewById(R.id.memoryDescription);
            location = itemView.findViewById(R.id.memoryLocation);
            image = itemView.findViewById(R.id.memoryImage);
        }
    }
}
