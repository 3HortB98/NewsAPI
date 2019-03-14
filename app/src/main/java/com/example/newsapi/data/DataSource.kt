package com.example.newsapi.data

import com.example.newsapi.Article
import io.reactivex.Maybe

interface DataSource{
    fun getTopNewsHeadlines(source: String, apiKey: String): Maybe<List<Article>>
    fun addNewsHeadline(article: Article)
}