package com.ader.githubsearchapp.presentation.ui.navigation

import com.ader.githubsearchapp.domain.model.UserModel

interface ISearchUserFragmentNavigation {
    fun navigateToUserDetail(userModel: UserModel)
}