package com.example.newsapi.data

import android.arch.persistence.room.*
import com.example.newsapi.Article
import io.reactivex.Maybe

@Dao
interface NewsArticleDao{
    @Query("SELECT * FROM articles")
    fun getAllArticles(): Maybe<List<Article>>

    @Insert
    fun addArticle(article: Article)

    @Update
    fun updateArticle(article: Article)

    @Delete
    fun deleteArtice(article: Article)
}