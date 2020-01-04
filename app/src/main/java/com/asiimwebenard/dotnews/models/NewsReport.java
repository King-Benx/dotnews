package com.asiimwebenard.dotnews.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


public class NewsReport implements Parcelable {
    private String status;
    private Long totalResults;
    private List<Article> articles = null;


    private NewsReport(Parcel in) {
        status = in.readString();
        if (in.readByte() == 0) {
            totalResults = null;
        } else {
            totalResults = in.readLong();
        }
    }

    public static final Creator<NewsReport> CREATOR = new Creator<NewsReport>() {
        @Override
        public NewsReport createFromParcel(Parcel in) {
            return new NewsReport(in);
        }

        @Override
        public NewsReport[] newArray(int size) {
            return new NewsReport[size];
        }
    };

    public List<Article> getArticles() {
        return articles;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(status);
        parcel.writeLong(totalResults);
        parcel.writeList(articles);
    }
}
