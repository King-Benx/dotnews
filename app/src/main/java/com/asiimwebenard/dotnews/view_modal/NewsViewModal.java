package com.asiimwebenard.dotnews.view_modal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.asiimwebenard.dotnews.R;
import com.asiimwebenard.dotnews.dao.StoredArticle;
import com.asiimwebenard.dotnews.models.NewsReport;
import com.asiimwebenard.dotnews.repository.NewsRepository;

import java.util.List;

public class NewsViewModal extends AndroidViewModel {

    private final LiveData<NewsReport> newsReportObservable;
    private final LiveData<NewsReport> topHeadlinesObservable;
    private LiveData<List<StoredArticle>> storedArticlesObservable;
    private NewsRepository newsRepository;

    private NewsViewModal(@NonNull Application application, NewsRepository newsRepository) {
        super(application);
        this.newsRepository = newsRepository;
        newsReportObservable = newsRepository.getNewsUpdates(application.getString(R.string.apiKey),"Uganda");
        topHeadlinesObservable = newsRepository.getTopHeadlines(application.getString(R.string.apiKey),"bbc-news" );
        storedArticlesObservable = newsRepository.getStoredArticles();
    }


    public LiveData<NewsReport> getNewsReportObservable(){
        return newsReportObservable;
    }

    public LiveData<NewsReport> getTopHeadlinesObservable(){
        return topHeadlinesObservable;
    }

    public LiveData<List<StoredArticle>> getStoredArticlesObservable(){
        return storedArticlesObservable;
    }

    public void storeArticle(StoredArticle storedArticle){
        newsRepository.storeArticle(storedArticle);
    }

    public void deleteArticle(StoredArticle storedArticle){
        newsRepository.deleteArticle(storedArticle);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory{
        @NonNull
        private final Application application;

        public Factory(@NonNull Application application) {
            this.application = application;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new NewsViewModal(application, new NewsRepository(application));
        }
    }
}


