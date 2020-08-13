package com.ader.githubsearchapp.di.module

import com.ader.githubsearchapp.presentation.ui.MainActivity
import com.ader.githubsearchapp.presentation.ui.fragments.SearchUserFragment
import com.ader.githubsearchapp.presentation.ui.fragments.UserDetailsFragment
import dagger.Module

import dagger.android.ContributesAndroidInjector

@Module
abstract class ControllerBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [SearchUserFragmentModule::class])
    abstract fun contributeSearchFragment(): SearchUserFragment

    @ContributesAndroidInjector(modules = [UserDetailFragmentModule::class])
    abstract fun contributeUserDetailsFragment(): UserDetailsFragment
}