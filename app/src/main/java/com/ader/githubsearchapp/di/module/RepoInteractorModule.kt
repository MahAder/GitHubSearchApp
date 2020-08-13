package com.ader.githubsearchapp.di.module

import com.ader.githubsearchapp.data.repository.IRepoRepository
import com.ader.githubsearchapp.di.scope.UserDetailsScope
import com.ader.githubsearchapp.domain.interactor.IRepoInteractor
import com.ader.githubsearchapp.domain.interactor.RepoInteractor
import dagger.Module
import dagger.Provides

@Module(includes = [RepoRepositoryModule::class])
class RepoInteractorModule {
    @Provides
    fun provideRepoInteractor(repoRepository: IRepoRepository): IRepoInteractor{
        return RepoInteractor(repoRepository)
    }
}