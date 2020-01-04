package com.asiimwebenard.dotnews.views;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.asiimwebenard.dotnews.R;
import com.asiimwebenard.dotnews.dao.StoredArticle;
import com.asiimwebenard.dotnews.view_modal.NewsViewModal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 */
public class SavedDetails extends Fragment {


    public SavedDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_details, container, false );
        ImageView imageView = view.findViewById(R.id.newsImage);
        TextView headline = view.findViewById(R.id.headlineTextView);
        TextView author = view.findViewById(R.id.authorTextView);
        TextView description = view.findViewById(R.id.descriptionTextView);
        TextView source = view.findViewById(R.id.sourceTextView);
        TextView url = view.findViewById(R.id.sourceUrlTextView);
        TextView publish = view.findViewById(R.id.publishDateTextView);
        FloatingActionButton shareButton = view.findViewById(R.id.floatingActionShareButton);
        FloatingActionButton deleteButton = view.findViewById(R.id.floatingActionDeleteButton);

        NewsViewModal.Factory factory = new NewsViewModal.Factory(Objects.requireNonNull(this.getActivity()).getApplication());
        NewsViewModal newsViewModal = new ViewModelProvider(this.getActivity(), factory).get(NewsViewModal.class);


        assert getArguments() != null;
        StoredArticle storedArticle = SavedDetailsArgs.fromBundle(getArguments()).getStoredArticle();
        Picasso.with(getContext()).load(storedArticle.getUrlToImage()).into(imageView);
        headline.setText(storedArticle.getTitle());
        author.setText(storedArticle.getAuthor());
        description.setText(storedArticle.getDescription());
        source.setText(storedArticle.getSource());
        url.setText(storedArticle.getUrl());
        publish.setText(storedArticle.getPublishedAt());

        shareButton.setOnClickListener(view1 -> {
            String message = storedArticle.getTitle()+"\n"+storedArticle.getDescription()+"\n"+storedArticle.getUrl();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, message);
            intent.setType("text/plain");
            if(intent.resolveActivity(Objects.requireNonNull(getActivity()).getPackageManager()) != null){
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(view12 -> {
            newsViewModal.deleteArticle(storedArticle);
            Toasty.info(Objects.requireNonNull(getContext()), "Successfully deleted "+storedArticle.getTitle()).show();
            Navigation.findNavController(view).navigateUp();
        });
        return view;
    }

}
