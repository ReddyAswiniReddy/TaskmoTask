package com.example.taskmo.utils



object ApiParams {

    object PostList{
        const val Api = "posts"
    }

    object Comments{
        const val ID = "id"
        const val Api = "posts/{post_id}/comments"
    }
}