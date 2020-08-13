package com.ader.githubsearchapp.presentation.model

interface UIUserModel{
    fun getUserName(): String
    fun getUserImgUrl(): String
    fun getUserRepoCount(): Int
}