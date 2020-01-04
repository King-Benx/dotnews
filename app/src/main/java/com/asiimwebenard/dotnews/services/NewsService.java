package com.asiimwebenard.dotnews.services;

import com.asiimwebenard.dotnews.models.NewsReport;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface NewsService {
    @GET("everything")
    Call<NewsReport> getNewsUpdates(@QueryMap HashMap<String, String> filter);

    @GET("top-headlines")
    Call<NewsReport> getTopHeadlines(@QueryMap HashMap<String, String> filter);

}
