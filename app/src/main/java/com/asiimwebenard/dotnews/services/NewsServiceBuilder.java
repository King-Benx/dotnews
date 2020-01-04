package com.asiimwebenard.dotnews.services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsServiceBuilder {

    private NewsServiceBuilder(){

    }
    /**
     * The base url to the API
     */
    private static final String URL = "https://newsapi.org/v2/";

    /**
     * Create logger.
     */
    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    /**
     * Create OkHttp Client.
     */
    private static OkHttpClient.Builder okhttp = new OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor);


    /**
     * The retrofit instance
     */
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okhttp.build())
            .build();

    /**
     * Helper method to help us build services and creates an implementation of our interfaces.
     *
     * @param serviceType object of service.
     * @param <S>         generic type.
     * @return retrofit.create(serviceType)
     */
    public static <S> S builderService(Class<S> serviceType) {
        return retrofit.create(serviceType);
    }
}
