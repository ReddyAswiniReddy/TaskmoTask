package com.example.taskmo.di.presenter

import com.example.taskmo.di.app.AppComponent
import com.example.taskmo.di.viewmodel.ViewModelFactoryModule
import com.example.taskmo.di.viewmodel.ViewModelModule
import com.example.taskmo.presentation.common.MainActivity
import com.example.taskmo.presentation.main.comments.CommentsListFragment
import com.example.taskmo.presentation.main.favorites.FavoritesFragment
import com.example.taskmo.presentation.main.posts.PostListFragment
import dagger.Component

@Component(
    dependencies = [AppComponent::class],
    modules = [ActivityModule::class, ViewModelModule::class, ViewModelFactoryModule::class]
)
@PerPresenter
interface PresenterComponent {
    //injectDagger activity
    fun inject(presenter: MainActivity)

    //fragment
    fun inject(presenter: PostListFragment)
    fun inject(presenter: FavoritesFragment)
    fun inject(presenter: CommentsListFragment)

}
