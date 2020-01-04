package com.asiimwebenard.dotnews.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;


@Dao
public interface StoredArticleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void  storeArticle(StoredArticle storedArticle);

    @Query("Select * FROM stored_articles")
    LiveData<List<StoredArticle>> savedArticles();

    @Delete
    void deleteArticle(StoredArticle storedArticle);
}
