package com.asiimwebenard.dotnews.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {StoredArticle.class}, version = 1, exportSchema = false)
public abstract class StoredArticleDatabase extends RoomDatabase {

    public abstract StoredArticleDao storedArticleDao();

    private static volatile StoredArticleDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static StoredArticleDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StoredArticleDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StoredArticleDatabase.class, "article_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
