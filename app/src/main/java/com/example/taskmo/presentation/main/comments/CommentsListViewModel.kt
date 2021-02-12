package com.example.taskmo.presentation.main.comments

import androidx.lifecycle.ViewModel
import com.example.taskmo.data.account.entity.UserFavoritePosts
import com.example.taskmo.data.account.entity.UserPosts
import com.example.taskmo.domain.repository.AccountRepository
import com.example.taskmo.domain.repository.Comments
import com.example.taskmo.presentation.base.Resource
import com.example.taskmo.presentation.base.SingleLiveEvent
import com.example.taskmo.presentation.base.Status
import com.example.taskmo.utils.Const
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CommentsListViewModel @Inject constructor(private val accountRepository: AccountRepository) :ViewModel(){


    internal val CommentsLiveEvent = SingleLiveEvent<Resource<ArrayList<Comments>>>()
    private var disposable:Disposable?=null

    var userPost = UserPosts()

    fun callUserCommentsListApi() {

        CommentsLiveEvent.value = Resource(Status.LOADING)

        disposable=accountRepository.getCommentList(userPost.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                CommentsLiveEvent.value = Resource(Status.SUCCESS, it as ArrayList<Comments>)
            }, {
                CommentsLiveEvent.value = Resource(Status.ERROR, it)
            })
    }

    fun manageFavoriteData(isFavorite:Boolean,isConnectionAvailable:Boolean){
        if(isFavorite){
            val favoritePosts = UserFavoritePosts()
            favoritePosts?.apply {
                this.body = userPost.body
                this.id = userPost.id
                this.title = userPost.title
                this.userId = userPost.userId
                this.uploadedOnServer = isConnectionAvailable
            }
             accountRepository.addFavoritePost(favoritePosts)
        }else{
            accountRepository.removeFavoritePost(userPost.id)
        }
    }

    fun checkUserPostAddedInFavorites():Boolean{
        val userPost =  accountRepository.getUserFavorite()?.find { it.id == userPost.id }
        return userPost != null
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

}