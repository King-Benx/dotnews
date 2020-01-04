package com.asiimwebenard.dotnews.views;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asiimwebenard.dotnews.R;
import com.asiimwebenard.dotnews.adapter.StoredArticlesAdapter;
import com.asiimwebenard.dotnews.dao.StoredArticle;
import com.asiimwebenard.dotnews.helpers.SavedRecyclerViewListener;
import com.asiimwebenard.dotnews.view_modal.NewsViewModal;

import java.util.Objects;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 */
public class Saved extends Fragment {


    public Saved() {
        // Required empty public constructor
    }

    private StoredArticlesAdapter storedArticlesAdapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved, container, false );
        recyclerView = view.findViewById(R.id.recyclerView);
        TextView titleHeader = view.findViewById(R.id.title);
        titleHeader.setText(Objects.requireNonNull(this.getActivity()).getResources().getString(R.string.saved_articles));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        NewsViewModal.Factory factory = new NewsViewModal.Factory(Objects.requireNonNull(this.getActivity()).getApplication());
        NewsViewModal newsViewModal = new ViewModelProvider(this.getActivity(), factory).get(NewsViewModal.class);

        observeViewModel(newsViewModal);
        return view;
    }

    private void observeViewModel(NewsViewModal newsViewModal) {
        if (this.getActivity() != null) {
            newsViewModal.getStoredArticlesObservable().observe(this.getActivity(), newsReport -> {
                if (newsReport != null) {
                    storedArticlesAdapter = new StoredArticlesAdapter(newsReport, new SavedRecyclerViewListener() {

                        @Override
                        public void onItemClick(StoredArticle storedArticle) {
                            Navigation.findNavController(Objects.requireNonNull(getView())).navigate(SavedDirections.actionSavedToSavedDetails(storedArticle));
                        }

                        @Override
                        public void onShareItemClick(StoredArticle storedArticle) {
                            String message = storedArticle.getTitle()+"\n"+storedArticle.getDescription()+"\n"+storedArticle.getUrl();
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_TEXT, message);
                            intent.setType("text/plain");
                            if(intent.resolveActivity(Objects.requireNonNull(getActivity()).getPackageManager()) != null){
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onDeleteItemClick(StoredArticle storedArticle) {
                            newsViewModal.deleteArticle(storedArticle);
                            Toasty.info(Objects.requireNonNull(getContext()), "Successfully deleted "+storedArticle.getTitle()).show();
                        }
                    });
                    recyclerView.setAdapter(storedArticlesAdapter);
                }
            });
        }
    }

}
