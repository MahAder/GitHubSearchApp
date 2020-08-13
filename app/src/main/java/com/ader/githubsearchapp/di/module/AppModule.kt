package com.ader.githubsearchapp.di.module

import android.content.Context
import com.ader.githubsearchapp.GitHubSearchApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideContext(application: GitHubSearchApplication): Context {
        return application
    }
}