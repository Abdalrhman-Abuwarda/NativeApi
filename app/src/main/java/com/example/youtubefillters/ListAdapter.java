package com.example.youtubefillters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.youtubefillters.databinding.ItemResultBinding;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private final List<Fillters> List;

    public ListAdapter(List<Fillters> List) {
        this.List = List;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemResultBinding binding = ItemResultBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fillters currentResult = List.get(position);

        holder.binding.videoTitle.setText(currentResult.getTitle());
        holder.binding.videoDescription.setText(currentResult.getDescription());
        holder.binding.videoPublishedDate.setText(currentResult.getPublishedAt());
        holder.binding.videoChanelTitle.setText(currentResult.getChannelTitle());

//        Log.v("ThumbnailUrl", currentBook.getThumbnailUrl());

        Glide.with(holder.itemView.getContext())
                .load(currentResult.getThumbnailUrl())
                .placeholder(R.drawable._default)
                .into(holder.binding.videoThumbnail);

//        new LoadImage(holder.binding.bookThumbnail).execute(currentBook.getThumbnailUrl());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemResultBinding binding;


        public ViewHolder(ItemResultBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
