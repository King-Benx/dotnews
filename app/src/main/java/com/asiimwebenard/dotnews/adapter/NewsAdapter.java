package com.asiimwebenard.dotnews.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asiimwebenard.dotnews.R;
import com.asiimwebenard.dotnews.helpers.RecyclerViewListener;
import com.asiimwebenard.dotnews.models.Article;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;


public class NewsAdapter extends
        RecyclerView.Adapter<NewsAdapter.Holder> {
    private List<Article> articles;
    private final RecyclerViewListener recyclerViewListener;

    public NewsAdapter(List<Article> articles, RecyclerViewListener recyclerViewListener) {
        this.articles= articles;
        this.recyclerViewListener = recyclerViewListener;
    }

    public void updateData(List<Article> articles){
        this.articles = articles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_article, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.Holder holder, int position) {
        holder.bind(articles.get(position), recyclerViewListener);
    }


    @Override
    public int getItemCount() {
        return articles.size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView headline;
        TextView description;
        FloatingActionButton shareButton;
        FloatingActionButton saveButton;

        Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.newsImage);
            headline = itemView.findViewById(R.id.headlineTextView);
            description = itemView.findViewById(R.id.descriptionTextView);
            shareButton = itemView.findViewById(R.id.floatingActionShareButton);
            saveButton = itemView.findViewById(R.id.floatingActionSaveButton);
        }

        void bind(Article article, RecyclerViewListener recyclerViewListener) {
            headline.setText(article.getTitle());
            description.setText(article.getDescription());
            Picasso.with(itemView.getContext()).load(article.getUrlToImage()).into(imageView);
            itemView.setOnClickListener(view -> recyclerViewListener.onItemClick(article));
            shareButton.setOnClickListener(view -> recyclerViewListener.onShareItemClick(article));

            saveButton.setOnClickListener(view -> recyclerViewListener.onSaveItemClick(article));
        }
    }


}
