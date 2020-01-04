package com.asiimwebenard.dotnews.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.asiimwebenard.dotnews.dao.StoredArticle;
import com.asiimwebenard.dotnews.dao.StoredArticleDao;
import com.asiimwebenard.dotnews.dao.StoredArticleDatabase;
import com.asiimwebenard.dotnews.models.NewsReport;
import com.asiimwebenard.dotnews.services.NewsService;
import com.asiimwebenard.dotnews.services.NewsServiceBuilder;


import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private StoredArticleDao storedArticleDao;
    private LiveData<List<StoredArticle>> storedArticles;
    private NewsService newsService;

    public NewsRepository(Application application){
        StoredArticleDatabase storedArticleDatabase = StoredArticleDatabase.getDatabase(application);
        storedArticleDao = storedArticleDatabase.storedArticleDao();
        this.storedArticles = storedArticleDao.savedArticles();
    }

    public LiveData<List<StoredArticle>> getStoredArticles(){
        return  storedArticles;
    }

    public void storeArticle(StoredArticle storedArticle){
        StoredArticleDatabase.databaseWriteExecutor.execute(()-> storedArticleDao.storeArticle(storedArticle));
    }

    public void deleteArticle(StoredArticle storedArticle){
        StoredArticleDatabase.databaseWriteExecutor.execute(()-> storedArticleDao.deleteArticle(storedArticle));
    }

    public LiveData<NewsReport> getNewsUpdates(String key, String location){
        final MutableLiveData<NewsReport> data = new MutableLiveData<>();
        newsService = NewsServiceBuilder.builderService(NewsService.class);
        HashMap<String, String> filter = new HashMap<>();
        filter.put("apiKey",key);
        filter.put("q", location);
        newsService.getNewsUpdates(filter).enqueue(new Callback<NewsReport>() {
            @Override
            public void onResponse(@NotNull Call<NewsReport> call, @NotNull Response<NewsReport> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<NewsReport> call, @NotNull Throwable t) {

            }
        });
        return data;
    }

    public LiveData<NewsReport> getTopHeadlines(String key, String source){
        final MutableLiveData<NewsReport> data = new MutableLiveData<>();
        newsService = NewsServiceBuilder.builderService(NewsService.class);
        HashMap<String, String> filter = new HashMap<>();
        filter.put("apiKey",key);
        filter.put("sources", source);
        newsService.getTopHeadlines(filter).enqueue(new Callback<NewsReport>() {
            @Override
            public void onResponse(@NotNull Call<NewsReport> call, @NotNull Response<NewsReport> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<NewsReport> call, @NotNull Throwable t) {

            }
        });
        return data;
    }
}
