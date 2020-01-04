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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.asiimwebenard.dotnews.R;
import com.asiimwebenard.dotnews.adapter.NewsAdapter;
import com.asiimwebenard.dotnews.dao.StoredArticle;
import com.asiimwebenard.dotnews.helpers.RecyclerViewListener;
import com.asiimwebenard.dotnews.models.Article;
import com.asiimwebenard.dotnews.view_modal.NewsViewModal;

import java.util.Objects;
import java.util.Random;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 */
public class International extends Fragment {


    public International() {
        // Required empty public constructor
    }

    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_international, container, false );
        recyclerView = view.findViewById(R.id.recyclerView);
        TextView titleHeader = view.findViewById(R.id.titleHeader);
        titleHeader.setText(Objects.requireNonNull(this.getActivity()).getResources().getString(R.string.international_news));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        NewsViewModal.Factory factory = new NewsViewModal.Factory(Objects.requireNonNull(this.getActivity()).getApplication());
        NewsViewModal newsViewModal = new ViewModelProvider(this.getActivity(), factory).get(NewsViewModal.class);

        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(() -> newsViewModal.getTopHeadlinesObservable().observe(getActivity(), newsReport ->
        {
            newsAdapter.updateData(newsReport.getArticles());
            swipeRefreshLayout.setRefreshing(false);
        }));


        observeViewModel(newsViewModal);
        return view;
    }

    private void observeViewModel(NewsViewModal newsViewModal) {
        if(this.getActivity() != null) {
            newsViewModal.getTopHeadlinesObservable().observe(this.getActivity(), newsReport -> {
                if (newsReport != null) {
                    newsAdapter = new NewsAdapter(newsReport.getArticles(), new RecyclerViewListener() {
                        @Override
                        public void onItemClick(Article article) {
                            Navigation.findNavController(Objects.requireNonNull(getView())).navigate(InternationalDirections.actionInternationalToDetail(article));
                        }

                        @Override
                        public void onShareItemClick(Article article) {
                            String message = article.getTitle()+"\n"+article.getDescription()+"\n"+article.getUrl();
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_TEXT, message);
                            intent.setType("text/plain");
                            if(intent.resolveActivity(Objects.requireNonNull(getActivity()).getPackageManager()) != null){
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onSaveItemClick(Article article) {
                            int num = new Random().nextInt();
                            StoredArticle storedArticle = new StoredArticle(num+"",article.getSource().getName(),article.getAuthor(),article.getTitle(),article.getDescription(), article.getUrl(), article.getUrlToImage(),article.getPublishedAt());
                            newsViewModal.storeArticle(storedArticle);
                            Toasty.success(Objects.requireNonNull(getContext()),"Successfully Saved Article").show();
                        }
                    });
                    recyclerView.setAdapter(newsAdapter);
                }
            });
        }
    }
}
