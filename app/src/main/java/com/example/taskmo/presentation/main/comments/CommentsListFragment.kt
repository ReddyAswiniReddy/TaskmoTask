package com.example.taskmo.presentation.main.comments

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
import com.example.taskmo.di.presenter.ActivityModule
import com.example.taskmo.di.presenter.DaggerPresenterComponent
import com.example.taskmo.domain.repository.Comments
import com.example.taskmo.presentation.base.Resource
import com.example.taskmo.presentation.base.SafeObserver
import com.example.taskmo.presentation.base.Status
import com.example.taskmo.presentation.common.MainActivity
import com.example.taskmo.presentation.main.comments.adapter.CommentsAdapter
import com.example.taskmo.utils.hide
import com.example.taskmo.utils.show
import kotlinx.android.synthetic.main.fragment_post_list.*
import javax.inject.Inject

class CommentsListFragment : Fragment() ,View.OnClickListener{

    companion object {
        fun getInstance(posts: UserPosts): CommentsListFragment {
            val fragment = CommentsListFragment()
            val bundle = Bundle()
            bundle.putParcelable("POST", posts)
            fragment.arguments = bundle
            return fragment
        }
    }


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(
            this, viewModelFactory
        )[CommentsListViewModel::class.java]
    }

    private val  commentsAdapter by lazy { CommentsAdapter(::onPostClick) }

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

        getBundle()
        setHeader()
        rvPostList.adapter = commentsAdapter
    }

    private fun getBundle() {
        val bundle = arguments
        if (bundle != null) {
            if (bundle.containsKey("POST")) {
                viewModel.userPost = bundle.getParcelable("POST")!!
            }
        }
    }

    private fun setHeader() {
        tvTitle.text = resources.getString(R.string.comments)
        ivBack.show()
        tvFavorite.show()
        tvFavorite.setOnClickListener(this)
        ivBack.setOnClickListener(this)

        if(viewModel.checkUserPostAddedInFavorites()){
            tvFavorite.text = resources.getString(R.string.favoritted)
            tvFavorite.isChecked = true
        }else{
            tvFavorite.text = resources.getString(R.string.favorite)
            tvFavorite.isChecked = false
        }
    }

    private fun injectFragment(){
        DaggerPresenterComponent.builder()
            .appComponent(AppController.instance.appComponent)
            .activityModule(ActivityModule(requireActivity()))
            .build().inject(this)
    }

    private fun initLiveDataObserver(){
        viewModel.CommentsLiveEvent.observe(
            viewLifecycleOwner,
            SafeObserver(this::observerPosts)
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.callUserCommentsListApi()
    }

    private fun observerPosts(response: Resource<ArrayList<Comments>>) {
        when (response.status) {
            Status.SUCCESS -> {
                progressBar?.hide()
                handleUserPost(response.item)
            }
            Status.ERROR -> {
                progressBar?.hide()
            }
            Status.LOADING -> {
                progressBar?.show()
            }
        }
    }

    private fun handleUserPost(userPost: ArrayList<Comments>?) {
        tvNoData.text = resources.getString(R.string.no_data_found)
        tvNoData.isVisible = userPost.isNullOrEmpty()
        commentsAdapter?.addData(userPost as ArrayList<Comments>)
    }

    private fun onPostClick(comment:Comments) {

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tvFavorite -> handleFavoriteUnFavorite()
            R.id.ivBack -> activity?.onBackPressed()
        }
    }

    private fun handleFavoriteUnFavorite() {
        if(tvFavorite.isChecked){
            tvFavorite.text = resources.getString(R.string.favorite)
            tvFavorite.isChecked = false
        }else{
            tvFavorite.text = resources.getString(R.string.favoritted)
            tvFavorite.isChecked = true
        }

        viewModel.manageFavoriteData(tvFavorite.isChecked, (requireActivity() as MainActivity)?.isConnectionAvailable)
    }

}