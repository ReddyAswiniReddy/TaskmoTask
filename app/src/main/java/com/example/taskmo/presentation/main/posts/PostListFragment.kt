package com.example.taskmo.presentation.main.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.taskmo.R
import com.example.taskmo.app.AppController
import com.example.taskmo.data.account.entity.UserPosts
import com.example.taskmo.di.presenter.DaggerPresenterComponent
import com.example.taskmo.presentation.base.Resource
import com.example.taskmo.presentation.base.SafeObserver
import com.example.taskmo.presentation.base.Status
import com.example.taskmo.presentation.main.comments.CommentsListFragment
import com.example.taskmo.presentation.main.posts.adapter.PostsAdapter
import com.example.taskmo.utils.addFragment
import com.example.taskmo.utils.invisible
import kotlinx.android.synthetic.main.fragment_post_list.*
import javax.inject.Inject

class PostListFragment : Fragment() {

    companion object {
        fun getInstance(): PostListFragment {
            val fragment = PostListFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(
            this, viewModelFactory
        )[PostListViewModel::class.java]
    }

    private val postsAdapter by lazy { PostsAdapter(::onPostClick) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        injectFragment()
        initLiveDataObserver()

        setHeader()
        rvPostList.adapter = postsAdapter
    }

    private fun setHeader() {
        tvTitle.text = resources.getString(R.string.posts)
        ivBack.invisible()
    }

    private fun injectFragment() {
        DaggerPresenterComponent.builder()
            .appComponent(AppController.instance.appComponent)
            .build().inject(this)
    }

    private fun initLiveDataObserver() {
        viewModel.userPostListLiveEvent.observe(
            viewLifecycleOwner,
            SafeObserver(this::observerUserPostLocal)
        )

        viewModel.userPostsLiveEvent.observe(
            viewLifecycleOwner,
            SafeObserver(this::observerPosts)
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.callUserPostListApi()
    }

    private fun observerUserPostLocal(userPost: List<UserPosts>?) {
        handleUserPost(userPost as ArrayList<UserPosts>)
    }

    private fun observerPosts(response: Resource<ArrayList<UserPosts>>) {
        when (response.status) {
            Status.SUCCESS -> {
//                progressBar?.hide()
                handleUserPost(response.item)
            }
            Status.ERROR -> {
//                progressBar?.hide()
            }
            Status.LOADING -> {
//                progressBar?.show()
            }
        }
    }

    private fun handleUserPost(userPost: ArrayList<UserPosts>?) {
        tvNoData.text = resources.getString(R.string.no_data_found)
        tvNoData.isVisible = userPost.isNullOrEmpty()
        postsAdapter?.addData(userPost as ArrayList<UserPosts>)
    }

    private fun onPostClick(userPost: UserPosts) {
        requireActivity().addFragment(CommentsListFragment.getInstance(userPost),addToBackStack = true)
    }
}