package com.example.taskmo.presentation.main.comments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmo.R
import com.example.taskmo.domain.repository.Comments
import kotlinx.android.synthetic.main.row_comments.view.*
import java.util.*


class CommentsAdapter(private val onPostClick: (item:Comments) -> Unit) :
    RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {


    var postList = ArrayList<Comments>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_comments, parent, false)
        return CommentViewHolder(view)
    }

    fun addData(PostList: ArrayList<Comments>) {
        this.postList.addAll(PostList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {

        val item = postList[position]
        item.let {
            holder.itemView.tvName.text = item.name
            holder.itemView.tvEmail.text = item.email
            holder.itemView.tvBody.text = item.body

            holder.itemView.cvComment.setOnClickListener {
                onPostClick.invoke(item)
            }
        }
    }


    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
        return postList.size
    }

}