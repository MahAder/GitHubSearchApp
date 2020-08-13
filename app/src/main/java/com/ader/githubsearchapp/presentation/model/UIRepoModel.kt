package com.ader.githubsearchapp.presentation.model

interface UIRepoModel{
    fun getRepoName(): String
    fun getRepoHtmlUrl(): String
    fun getRepoForksCount(): Int
    fun getRepoStarsCount(): Int
}