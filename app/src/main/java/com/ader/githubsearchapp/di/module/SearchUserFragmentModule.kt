package com.ader.githubsearchapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ader.githubsearchapp.domain.interactor.IUserIntreactor
import com.ader.githubsearchapp.presentation.ui.fragments.SearchUserFragment
import com.ader.githubsearchapp.presentation.viewmodel.UserSearchViewModel
import dagger.Module
import dagger.Provides

@Module(includes = [UserInteractorModule::class])
class SearchUserFragmentModule {
    @Provides
    fun provideViewModelFactory(
        userInteractor: IUserIntreactor
    ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserSearchViewModel(userInteractor) as T
        }
    }

    @Provides
    fun provideSearchViewModel(
        fragment: SearchUserFragment,
        vmFactory: ViewModelProvider.Factory
    ): UserSearchViewModel{
        return ViewModelProviders.of(fragment, vmFactory).get(UserSearchViewModel::class.java)
    }
}