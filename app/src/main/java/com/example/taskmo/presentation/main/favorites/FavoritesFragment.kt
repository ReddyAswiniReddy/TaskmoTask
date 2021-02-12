package com.example.taskmo.presentation.main.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.taskmo.R
import com.example.taskmo.app.AppController
import com.example.taskmo.data.account.entity.UserFavoritePosts
import com.example.taskmo.data.account.entity.UserPosts
import com.example.taskmo.di.presenter.ActivityModule
import com.example.taskmo.di.presenter.DaggerPresenterComponent
import com.example.taskmo.presentation.base.SafeObserver
import com.example.taskmo.presentation.main.comments.CommentsListFragment
import com.example.taskmo.presentation.main.favorites.adapter.FavoritePostsAdapter
import com.example.taskmo.presentation.main.posts.adapter.PostsAdapter
import com.example.taskmo.utils.addFragment
import com.example.taskmo.utils.hide
import com.example.taskmo.utils.invisible
import com.example.taskmo.utils.show
import kotlinx.android.synthetic.main.fragment_post_list.*
import java.util.*
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    companion object {
        fun getInstance(): FavoritesFragment {
            val fragment = FavoritesFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val favoritePostsAdapter by lazy { FavoritePostsAdapter() }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(
            this, viewModelFactory
        )[FavoritesViewModel::class.java]
    }

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
        rvPostList.adapter = favoritePostsAdapter
    }

    private fun setHeader() {
        tvTitle.text = resources.getString(R.string.favorites)
        ivBack.invisible()
    }

    private fun initLiveDataObserver() {
        viewModel.userPostListLiveEvent.observe(
            viewLifecycleOwner,
            SafeObserver(this::observerFavPosts)
        )
    }

    private fun observerFavPosts(posts: List<UserFavoritePosts>?) {
        if (posts.isNullOrEmpty()) {
            tvNoData.show()
        } else {
            tvNoData.hide()
            favoritePostsAdapter?.addData(posts as ArrayList<UserFavoritePosts>)
        }
    }

    private fun injectFragment() {
        DaggerPresenterComponent.builder()
            .appComponent(AppController.instance.appComponent)
            .activityModule(ActivityModule(requireActivity()))
            .build().inject(this)
    }
}