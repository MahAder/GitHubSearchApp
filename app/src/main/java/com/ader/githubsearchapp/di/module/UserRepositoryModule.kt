package com.ader.githubsearchapp.di.module

import com.ader.githubsearchapp.data.network.GitUserApi
import com.ader.githubsearchapp.data.repository.IUserRepository
import com.ader.githubsearchapp.data.repository.UserRepository
import com.ader.githubsearchapp.di.scope.SearchScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [RetrofitModule::class])
class UserRepositoryModule {
    @Provides
    fun provideUserRepository(gitApi: GitUserApi): IUserRepository{
        return UserRepository(gitApi)
    }

    @Provides
    fun provideGitUserApi(retrofit: Retrofit): GitUserApi{
        return retrofit.create(GitUserApi::class.java)
    }
}