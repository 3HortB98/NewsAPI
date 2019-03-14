package com.example.newsapi

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.newsapi.UI.home.HomeViewModel
import com.example.newsapi.UI.home.NewsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val newsAdapter = NewsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvResults.layoutManager = LinearLayoutManager(this)
        rvResults.adapter= newsAdapter
        val homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val observer = Observer<List<Article>> {results->
            if (results != null) {
                newsAdapter.setData(results)
            }
        }


       homeViewModel.getArticles(application)
        homeViewModel.getArticleObservable().observe(this,observer)





    }
}
