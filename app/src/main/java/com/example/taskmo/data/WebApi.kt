package com.example.taskmo.data

import com.example.taskmo.data.account.entity.UserPosts
import com.example.taskmo.domain.repository.Comments
import com.example.taskmo.utils.ApiParams
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface WebApi {

    @GET(ApiParams.PostList.Api)
    fun getPostList(): Single<ArrayList<UserPosts>>

    @GET("posts/"+ "{${ApiParams.Comments.ID}}"+"/comments")
    fun getCommentList(@Path(ApiParams.Comments.ID) id: Int): Single<ArrayList<Comments>>

}