package com.example.taskmo.presentation.main.posts

import androidx.lifecycle.ViewModel
import com.example.taskmo.data.account.entity.UserPosts
import com.example.taskmo.domain.repository.AccountRepository
import com.example.taskmo.presentation.base.Resource
import com.example.taskmo.presentation.base.SingleLiveEvent
import com.example.taskmo.presentation.base.Status
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostListViewModel @Inject constructor(private val accountRepository: AccountRepository) :ViewModel(){

    internal val userPostsLiveEvent = SingleLiveEvent<Resource<ArrayList<UserPosts>>>()
    private var disposable:Disposable?=null
    val userPostListLiveEvent = accountRepository.getUserPostLiveData()

    fun callUserPostListApi() {

        userPostsLiveEvent.value = Resource(Status.LOADING)

        disposable=accountRepository.getPostList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userPostsLiveEvent.value = Resource(Status.SUCCESS, it as ArrayList<UserPosts>)
            }, {
                userPostsLiveEvent.value = Resource(Status.ERROR, it)
            })
    }


    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

}