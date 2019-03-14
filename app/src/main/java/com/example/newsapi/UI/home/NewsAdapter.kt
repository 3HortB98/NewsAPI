package com.example.newsapi.UI.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.newsapi.Article
import com.example.newsapi.R

class  NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){
    private val data = ArrayList<Article>()

    fun setData(articles: List<Article>){
        data.clear()
        data.addAll(articles)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news,parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(viewHolder: NewsViewHolder, position: Int) {
            viewHolder.tvAuthor.text = data[position].author
            viewHolder.tvTitle.text = data[position].title
        viewHolder.tvContent.text = data[position].content
        viewHolder.tvDescription.text = data[position].description
    }

    class NewsViewHolder( myView: View) : RecyclerView.ViewHolder(myView){
    val tvTitle: TextView = myView.findViewById(R.id.tvTitle)
    val tvAuthor: TextView = myView.findViewById(R.id.tvAuthor)
    val tvContent: TextView = myView.findViewById(R.id.tvContent)
    val tvDescription: TextView = myView.findViewById(R.id.tvDescription)
}
}