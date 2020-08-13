package com.ader.githubsearchapp.di.module

import com.ader.githubsearchapp.data.network.GitRepoApi
import com.ader.githubsearchapp.data.repository.RepoRepository
import com.ader.githubsearchapp.data.repository.IRepoRepository
import com.ader.githubsearchapp.di.scope.UserDetailsScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [RetrofitModule::class])
class RepoRepositoryModule(){
    @Provides
    fun provideGithubRepo(gitApi: GitRepoApi): IRepoRepository{
        return RepoRepository(gitApi)
    }

    @Provides
    fun provideGitRepoApi(retrofit: Retrofit): GitRepoApi{
        return retrofit.create(GitRepoApi::class.java)
    }
}