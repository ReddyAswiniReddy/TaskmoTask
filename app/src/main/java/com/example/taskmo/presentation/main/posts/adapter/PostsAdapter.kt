package com.example.taskmo.presentation.main.posts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmo.R
import com.example.taskmo.data.account.entity.UserPosts
import kotlinx.android.synthetic.main.row_posts.view.*
import java.util.*


class PostsAdapter(private val onPostClick: (item:UserPosts) -> Unit) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    var postList = ArrayList<UserPosts>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_posts, parent, false)
        return PostViewHolder(view)
    }

    fun addData(PostList: ArrayList<UserPosts>) {
        this.postList.addAll(PostList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val item = postList[position]
        item.let {
            holder.itemView.tvPostTitle.text = item.title
            holder.itemView.tvPostBody.text = item.body

            holder.itemView.cvPost.setOnClickListener {
                onPostClick.invoke(item)
            }
        }
    }


    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
        return postList.size
    }
}