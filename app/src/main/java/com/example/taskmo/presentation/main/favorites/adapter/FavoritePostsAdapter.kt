package com.example.taskmo.presentation.main.favorites.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmo.R
import com.example.taskmo.data.account.entity.UserFavoritePosts
import com.example.taskmo.data.account.entity.UserPosts
import kotlinx.android.synthetic.main.row_posts.view.*
import java.util.*


class FavoritePostsAdapter() : RecyclerView.Adapter<FavoritePostsAdapter.PostViewHolder>() {

    var postList = ArrayList<UserFavoritePosts>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_fav_posts, parent, false)
        return PostViewHolder(view)
    }

    fun addData(PostList: ArrayList<UserFavoritePosts>) {
        this.postList.addAll(PostList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val item = postList[position]
        item.let {
            holder.itemView.tvPostTitle.text = item.title
            holder.itemView.tvPostBody.text = item.body
        }
    }


    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
        return postList.size
    }
}