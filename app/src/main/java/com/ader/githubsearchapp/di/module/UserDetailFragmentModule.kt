package com.ader.githubsearchapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.ader.githubsearchapp.domain.interactor.IRepoInteractor
import com.ader.githubsearchapp.domain.interactor.IUserIntreactor
import com.ader.githubsearchapp.domain.model.UserModel
import com.ader.githubsearchapp.presentation.ui.fragments.SearchUserFragment
import com.ader.githubsearchapp.presentation.ui.fragments.UserDetailsFragment
import com.ader.githubsearchapp.presentation.ui.fragments.UserDetailsFragmentArgs
import com.ader.githubsearchapp.presentation.viewmodel.UserDetailsViewModel
import com.ader.githubsearchapp.presentation.viewmodel.UserSearchViewModel
import dagger.Module
import dagger.Provides

@Module(includes = [RepoInteractorModule::class])
class UserDetailFragmentModule {
    @Provides
    fun provideViewModelFactory(
        repoInteractor: IRepoInteractor,
        userModel: UserModel
    ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserDetailsViewModel(repoInteractor, userModel) as T
        }
    }

    @Provides
    fun provideUserDetailViewModel(
        fragment: UserDetailsFragment,
        vmFactory: ViewModelProvider.Factory
    ): UserDetailsViewModel {
        return ViewModelProviders.of(fragment, vmFactory).get(UserDetailsViewModel::class.java)
    }

    @Provides
    fun provideUserModel(fragment: UserDetailsFragment): UserModel{
        return fragment.run {
            val args: UserDetailsFragmentArgs by navArgs()
            args.userModel
        }
    }
}