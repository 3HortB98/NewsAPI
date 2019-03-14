package com.example.newsapi.data

import android.app.Application
import android.arch.persistence.room.Room
import com.example.newsapi.Article
import com.example.newsapi.DATABASE_NAME
import io.reactivex.Maybe

class LocalDataSource(private val application: Application): DataSource{
    private val database: NewsDatabase by lazy{
        Room.databaseBuilder(application,NewsDatabase::class.java, DATABASE_NAME)
            .build()
    }
    override fun getTopNewsHeadlines(source: String, apiKey: String)=database.newsArticleDao().getAllArticles()


    override fun addNewsHeadline(article: Article) {
        database.newsArticleDao().addArticle(article)
    }

}