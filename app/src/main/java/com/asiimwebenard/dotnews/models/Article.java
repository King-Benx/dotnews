package com.asiimwebenard.dotnews.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;

    public Article(Source source, String author, String title, String description, String url, String urlToImage, String publishedAt) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    protected Article(Parcel in) {
        source = in.readParcelable(Source.class.getClassLoader());
        author = in.readString();
        title = in.readString();
        description = in.readString();
        url = in.readString();
        urlToImage = in.readString();
        publishedAt = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public Source getSource() {
        return source;
    }


    public String getAuthor() {
        return author;
    }


    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }


    public String getUrlToImage() {
        return urlToImage;
    }


    public String getPublishedAt() {
        return publishedAt;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(source, i);
        parcel.writeString(author);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(url);
        parcel.writeString(urlToImage);
        parcel.writeString(publishedAt);
    }
}
