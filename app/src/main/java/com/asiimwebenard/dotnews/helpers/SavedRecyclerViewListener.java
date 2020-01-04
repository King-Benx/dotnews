package com.asiimwebenard.dotnews.helpers;

import com.asiimwebenard.dotnews.dao.StoredArticle;

public interface SavedRecyclerViewListener {
    void onItemClick(StoredArticle storedArticle);
    void onShareItemClick(StoredArticle storedArticle);
    void onDeleteItemClick(StoredArticle storedArticle);
}
