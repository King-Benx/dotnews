package com.asiimwebenard.dotnews.helpers;

import com.asiimwebenard.dotnews.models.Article;

public interface RecyclerViewListener {
    void onItemClick(Article article);
    void onShareItemClick(Article article);
    void onSaveItemClick(Article article);
}
