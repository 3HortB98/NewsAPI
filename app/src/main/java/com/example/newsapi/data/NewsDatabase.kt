package com.example.newsapi.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.newsapi.Article
import com.example.newsapi.DATABASE_VERSION

@Database(entities = [Article:: class],version = DATABASE_VERSION)
abstract class  NewsDatabase: RoomDatabase(){
    abstract fun newsArticleDao(): NewsArticleDao
}