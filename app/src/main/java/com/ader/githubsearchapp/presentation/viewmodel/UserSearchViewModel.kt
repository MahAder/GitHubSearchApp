package com.ader.githubsearchapp.presentation.viewmodel

import androidx.lifecycle.*
import com.ader.githubsearchapp.domain.interactor.IUserIntreactor
import com.ader.githubsearchapp.domain.model.UserModel
import com.ader.githubsearchapp.presentation.model.UIUserModel
import com.ader.githubsearchapp.presentation.ui.navigation.ISearchUserFragmentNavigation
import kotlinx.coroutines.launch

class UserSearchViewModel(private val userInteractor: IUserIntreactor): ViewModel() {
    val userLiveData = MediatorLiveData<List<UIUserModel>>()
    val searchQueryLiveData = MutableLiveData<String>()
    val errorLiveData = MutableLiveData<String>()

    var navigation: ISearchUserFragmentNavigation? = null

    init {
        userLiveData.addSource(searchQueryLiveData){
            if(it.isNotEmpty()) {
                searchUsers(it)
            }
        }
    }

    fun navigateToUserDetails(userUIUserModel: UIUserModel){
        navigation?.navigateToUserDetail(userUIUserModel as UserModel)
    }

    private fun searchUsers(query: String){
        viewModelScope.launch {
            val users = userInteractor.searchUsers(query){
                onApiError(it.message)
            }
            userLiveData.postValue(users)
        }
    }

    private fun onApiError(error: String?){
        errorLiveData.postValue(error ?: "")
    }
}