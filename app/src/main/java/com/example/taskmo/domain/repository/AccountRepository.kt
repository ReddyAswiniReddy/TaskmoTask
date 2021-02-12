package com.example.taskmo.domain.repository

import androidx.lifecycle.LiveData
import com.example.taskmo.data.account.entity.UserFavoritePosts
import com.example.taskmo.data.account.entity.UserPosts
import io.reactivex.Single

interface AccountRepository {

    fun getPostList():Single<ArrayList<UserPosts>>

    fun getCommentList(id:Int):Single<ArrayList<Comments>>

    fun getUserPostLiveData(): LiveData<List<UserPosts>?>

    fun getUserFavoritePostLiveData(): LiveData<List<UserFavoritePosts>?>

    fun getUserFavorite(): List<UserFavoritePosts>

    fun removeFavoritePost(id: Int)

    fun addFavoritePost(post:UserFavoritePosts)
}