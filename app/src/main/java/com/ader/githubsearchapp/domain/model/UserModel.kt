package com.ader.githubsearchapp.domain.model

import com.ader.githubsearchapp.presentation.model.UIUserDetailsModel
import java.io.Serializable

data class UserModel(
    val username: String,
    val bio: String?,
    val avatarUrl: String,
    val email: String?,
    val location: String?,
    val joinDate: String,
    val followersCount: Int,
    val followingCount: Int,
    val repositoriesCount: Int
): Serializable, UIUserDetailsModel{
    override fun getUserBio(): String? {
        return bio
    }

    override fun getUserEmail(): String? {
        return email
    }

    override fun getUserLocation(): String? {
        return location
    }

    override fun getUserJoinDate(): String {
        return joinDate
    }

    override fun getUserFollowersCount(): Int {
        return followersCount
    }

    override fun getUserFollowingCount(): Int {
        return followingCount
    }

    override fun getUserName(): String {
        return username
    }

    override fun getUserImgUrl(): String {
       return avatarUrl
    }

    override fun getUserRepoCount(): Int {
        return repositoriesCount
    }

}