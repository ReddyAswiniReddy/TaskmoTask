package com.example.taskmo.di.viewmodel

import androidx.lifecycle.ViewModel
import com.example.taskmo.di.app.CommonModule
import com.example.taskmo.di.viewmodel.ViewModelKey
import com.example.taskmo.presentation.main.comments.CommentsListViewModel
import com.example.taskmo.presentation.main.favorites.FavoritesViewModel
import com.example.taskmo.presentation.main.posts.PostListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

// to Inject ViewModel class
@Module(includes = [CommonModule::class])
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PostListViewModel::class)
    abstract fun bindPostListViewModel(viewModel: PostListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindFavoritesViewModel(viewModel: FavoritesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CommentsListViewModel::class)
    abstract fun bindCommentsListViewModel(viewModel: CommentsListViewModel): ViewModel


}
