package com.ader.githubsearchapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ader.githubsearchapp.domain.interactor.IRepoInteractor
import com.ader.githubsearchapp.domain.model.UserModel
import com.ader.githubsearchapp.presentation.model.UIRepoModel
import com.ader.githubsearchapp.presentation.model.UIUserDetailsModel
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val repoInteractor: IRepoInteractor,
    private val userModel: UserModel
) : ViewModel() {
    val repoLiveData = MutableLiveData<List<UIRepoModel>>()
    val userLiveData = MutableLiveData<UIUserDetailsModel>()
    val errorLiveData = MutableLiveData<String>()

    init {
        userLiveData.postValue(userModel)
        initUserRepo()
    }

    private fun initUserRepo() {
        viewModelScope.launch {
            val userRepo = repoInteractor.getRepoByUserName(userModel.username){
                errorLiveData.postValue(it.message)
            }
            repoLiveData.postValue(userRepo)
        }
    }
}