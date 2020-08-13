package com.ader.githubsearchapp.domain.interactor

import com.ader.githubsearchapp.Constants
import com.ader.githubsearchapp.data.model.UserDetailsResponseModel
import com.ader.githubsearchapp.data.repository.IUserRepository
import com.ader.githubsearchapp.domain.model.UserModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class UserInteractor(private val userRepository: IUserRepository) : IUserIntreactor {
    private val dateFormatter = SimpleDateFormat("dd-MM-yy")
    private val dateFormatterRead = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

    override suspend fun searchUsers(
        query: String,
        onErrorAction: (exception: java.lang.Exception) -> Unit
    ): List<UserModel> {
        val searchResponse = userRepository.searchUsers(query, onErrorAction)
        val userList = ArrayList<UserModel>()
        val max = if (searchResponse.size > Constants.SEARCH_MAX_SIZE) Constants.SEARCH_MAX_SIZE
        else searchResponse.size
        for (i in 0 until max) {
            val userDetails =
                userRepository.getUserDetailsByName(searchResponse[i].login, onErrorAction)
                    ?: break
            userDetails.let {
                userList.add(mapUserResponseToUserModel(searchResponse[i].login, it))
            }
        }

        return userList
    }

    private fun mapUserResponseToUserModel(
        username: String,
        userDetailsResponseModel: UserDetailsResponseModel
    ): UserModel {
        return UserModel(
            username,
            userDetailsResponseModel.bio,
            userDetailsResponseModel.avatarUrl,
            userDetailsResponseModel.email,
            userDetailsResponseModel.location,
            formatDate(userDetailsResponseModel.joinDate) ?: "",
            userDetailsResponseModel.followersCount,
            userDetailsResponseModel.followingCount,
            userDetailsResponseModel.repositoriesCount
        )
    }

    private fun formatDate(dateString: String?): String? {
        if (dateString == null) return ""
        val date = dateFormatterRead.parse(dateString) ?: return ""
        return dateFormatter.format(date)
    }
}