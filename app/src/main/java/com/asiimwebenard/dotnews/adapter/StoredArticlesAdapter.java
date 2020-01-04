package com.asiimwebenard.dotnews.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asiimwebenard.dotnews.R;
import com.asiimwebenard.dotnews.dao.StoredArticle;
import com.asiimwebenard.dotnews.helpers.SavedRecyclerViewListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StoredArticlesAdapter extends RecyclerView.Adapter<StoredArticlesAdapter.Holder> {

    private List<StoredArticle> storedArticles;
    private final SavedRecyclerViewListener savedRecyclerViewListener;

    public StoredArticlesAdapter(List<StoredArticle> storedArticle, SavedRecyclerViewListener savedRecyclerViewListener) {
        this.storedArticles = storedArticle;
        this.savedRecyclerViewListener = savedRecyclerViewListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_saved_news_item, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StoredArticlesAdapter.Holder holder, int position) {
        holder.bind(storedArticles.get(position), savedRecyclerViewListener);
    }

    @Override
    public int getItemCount() {
        return storedArticles.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView headline;
        TextView description;
        FloatingActionButton shareButton;
        FloatingActionButton deleteButton;

        Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.newsImage);
            headline = itemView.findViewById(R.id.headlineTextView);
            description = itemView.findViewById(R.id.descriptionTextView);
            shareButton = itemView.findViewById(R.id.floatingActionShareButton);
            deleteButton = itemView.findViewById(R.id.floatingActionDeleteButton);
        }

        void bind(StoredArticle storedArticle, SavedRecyclerViewListener savedRecyclerViewListener) {
                headline.setText(storedArticle.getTitle());
                description.setText(storedArticle.getDescription());
            Picasso.with(itemView.getContext()).load(storedArticle.getUrlToImage()).into(imageView);
            itemView.setOnClickListener(view -> savedRecyclerViewListener.onItemClick(storedArticle));
            shareButton.setOnClickListener(view -> savedRecyclerViewListener.onShareItemClick(storedArticle));
            deleteButton.setOnClickListener(view -> savedRecyclerViewListener.onDeleteItemClick(storedArticle));

        }
    }
}
