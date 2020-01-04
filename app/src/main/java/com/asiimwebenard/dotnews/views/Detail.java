package com.asiimwebenard.dotnews.views;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asiimwebenard.dotnews.R;
import com.asiimwebenard.dotnews.dao.StoredArticle;
import com.asiimwebenard.dotnews.models.Article;
import com.asiimwebenard.dotnews.view_modal.NewsViewModal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.Objects;
import java.util.Random;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 */
public class Detail extends Fragment {


    public Detail() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false );
        ImageView imageView = view.findViewById(R.id.newsImage);
        TextView headline = view.findViewById(R.id.headlineTextView);
        TextView author = view.findViewById(R.id.authorTextView);
        TextView description = view.findViewById(R.id.descriptionTextView);
        TextView source = view.findViewById(R.id.sourceTextView);
        TextView url = view.findViewById(R.id.sourceUrlTextView);
        TextView publish = view.findViewById(R.id.publishDateTextView);
        FloatingActionButton saveButton = view.findViewById(R.id.floatingActionSaveButton);
        FloatingActionButton shareButton = view.findViewById(R.id.floatingActionShareButton);

        NewsViewModal.Factory factory = new NewsViewModal.Factory(Objects.requireNonNull(this.getActivity()).getApplication());
        NewsViewModal newsViewModal = new ViewModelProvider(this.getActivity(), factory).get(NewsViewModal.class);

        assert getArguments() != null;
        Article article = DetailArgs.fromBundle(getArguments()).getArticle();
        Picasso.with(getContext()).load(article.getUrlToImage()).into(imageView);
        headline.setText(article.getTitle());
        author.setText(article.getAuthor());
        description.setText(article.getDescription());
        source.setText(article.getSource().getName());
        url.setText(article.getUrl());
        publish.setText(article.getPublishedAt());

        saveButton.setOnClickListener(view1 -> {
            int num = new Random().nextInt();
            StoredArticle storedArticle = new StoredArticle(num+"",article.getSource().getName(),article.getAuthor(),article.getTitle(),article.getDescription(), article.getUrl(), article.getUrlToImage(),article.getPublishedAt());
            newsViewModal.storeArticle(storedArticle);
            Toasty.success(Objects.requireNonNull(getContext()),"Successfully Saved Article").show();
        });

        shareButton.setOnClickListener(view12 -> {
            String message = article.getTitle()+"\n"+article.getDescription()+"\n"+article.getUrl();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, message);
            intent.setType("text/plain");
            if(intent.resolveActivity(Objects.requireNonNull(getActivity()).getPackageManager()) != null){
                startActivity(intent);
            }
        });

        return view;

    }

}
