package com.ader.githubsearchapp.presentation.model

interface UIUserDetailsModel: UIUserModel{
    fun getUserBio(): String?
    fun getUserEmail(): String?
    fun getUserLocation(): String?
    fun getUserJoinDate(): String
    fun getUserFollowersCount(): Int
    fun getUserFollowingCount(): Int
}