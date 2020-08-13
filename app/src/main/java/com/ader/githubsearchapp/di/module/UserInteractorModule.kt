package com.ader.githubsearchapp.di.module

import com.ader.githubsearchapp.data.repository.IUserRepository
import com.ader.githubsearchapp.di.scope.SearchScope
import com.ader.githubsearchapp.domain.interactor.IUserIntreactor
import com.ader.githubsearchapp.domain.interactor.UserInteractor
import dagger.Module
import dagger.Provides

@Module(includes = [UserRepositoryModule::class])
class UserInteractorModule {
    @Provides
    fun provideUserInteractor(userRepository: IUserRepository): IUserIntreactor{
        return UserInteractor(userRepository)
    }
}