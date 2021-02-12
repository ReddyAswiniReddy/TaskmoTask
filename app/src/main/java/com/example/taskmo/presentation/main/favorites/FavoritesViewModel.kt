package com.example.taskmo.presentation.main.favorites

import androidx.lifecycle.ViewModel
import com.example.taskmo.domain.repository.AccountRepository
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(private val accountRepository: AccountRepository) :
    ViewModel() {

    val userPostListLiveEvent = accountRepository.getUserFavoritePostLiveData()

}