package com.ader.githubsearchapp.di.component

import com.ader.githubsearchapp.GitHubSearchApplication
import com.ader.githubsearchapp.di.module.AppModule
import com.ader.githubsearchapp.di.module.ControllerBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    AppModule::class, ControllerBuilderModule::class])
interface AppComponent : AndroidInjector<GitHubSearchApplication> {
    override fun inject(application: GitHubSearchApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: GitHubSearchApplication): Builder

        fun build(): AppComponent
    }
}