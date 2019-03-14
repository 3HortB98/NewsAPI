package com.example.newsapi.UI.home

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.newsapi.API_KEY
import com.example.newsapi.Article
import com.example.newsapi.NEWS_SOURCE
import com.example.newsapi.data.DataSource
import com.example.newsapi.data.LocalDataSource
import com.example.newsapi.data.NewsRepository
import com.example.newsapi.data.RemoteDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel: ViewModel(){
    private val articleObservable: MutableLiveData<List<Article>> = MutableLiveData()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getArticleObservable():LiveData<List<Article>> = articleObservable

    private lateinit var repository: DataSource

    private lateinit var newsAdapter: NewsAdapter


    fun getArticles(application: Application){
        newsAdapter = NewsAdapter()
        repository = NewsRepository(remoteDataSource =  RemoteDataSource(),
            localDataSource = LocalDataSource(application))
        compositeDisposable.add(repository.getTopNewsHeadlines(NEWS_SOURCE, API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                newsAdapter.setData(it)

            },{
                it.printStackTrace()}))

    }

    /*fun getArticles(application: Application){
        repository = NewsRepository(remoteDataSource =  RemoteDataSource(),
            localDataSource = LocalDataSource(application))
        compositeDisposable.add(repository.getTopNewsHeadlines(NEWS_SOURCE, API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ Log.d("News Desciption: ",it[0].description)},{
                it.printStackTrace()}))
    }*/
}