package com.example.taskmo.data.account

import androidx.lifecycle.LiveData
import com.example.taskmo.data.WebApi
import com.example.taskmo.data.account.entity.UserFavoritePosts
import com.example.taskmo.data.account.entity.UserPosts
import com.example.taskmo.domain.repository.AccountRepository
import com.example.taskmo.domain.repository.Comments
import io.reactivex.Single
import javax.inject.Inject

class AccountDataRepository @Inject constructor(
    private val webApi: WebApi,
    private val accountDao: AccountDao,
    private val userFavoriteDao: UserFavoriteDao
) : AccountRepository {


    override fun getPostList(): Single<ArrayList<UserPosts>> {
        return webApi.getPostList()
            .doOnSuccess { it ->
                it?.let { postsResponse ->
                    postsResponse?.let {
                        accountDao.clearTable()
                        accountDao.addUserPosts(it)
                    }
                }
            }
    }

    override fun getCommentList(id:Int): Single<ArrayList<Comments>> {
        return webApi.getCommentList(id)
    }

    override fun getUserPostLiveData(): LiveData<List<UserPosts>?> {
        return accountDao.getUserPostLiveData()
    }

    override fun getUserFavoritePostLiveData(): LiveData<List<UserFavoritePosts>?> {
        return userFavoriteDao.getUserFavPostLiveData()
    }

    override fun getUserFavorite(): List<UserFavoritePosts> {
        return userFavoriteDao.getUserFavPosts()
    }

    override fun removeFavoritePost(id: Int) {
        userFavoriteDao.removeUserFavPost(id)
    }

    override fun addFavoritePost(post: UserFavoritePosts) {
       userFavoriteDao.addUserFavPost(post)
    }
}