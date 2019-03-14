package com.example.newsapi.data

import com.example.newsapi.Article
import com.example.newsapi.BASE_URL
import com.example.newsapi.net.NewsService
import io.reactivex.Maybe
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource: DataSource{

    private val newsService: NewsService by lazy {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        retrofit.create(NewsService::class.java)
    }
    override fun getTopNewsHeadlines(source: String, apiKey: String): Maybe<List<Article>> {
       return newsService.getTopHeadlines(source,apiKey)
            .flatMapMaybe { Maybe.just(it.articles) }
    }

    override fun addNewsHeadline(article: Article) {
        //NO_OP
    }

}