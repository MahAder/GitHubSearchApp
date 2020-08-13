package com.ader.githubsearchapp.domain.interactor

import com.ader.githubsearchapp.data.model.RepoResponseModel
import com.ader.githubsearchapp.data.repository.IRepoRepository
import com.ader.githubsearchapp.domain.model.RepoModel

class RepoInteractor(private val repoRepository: IRepoRepository) : IRepoInteractor {
    override suspend fun getRepoByUserName(
        username: String,
        onErrorAction: (exception: java.lang.Exception) -> Unit
    ): List<RepoModel> {
        val repoList = ArrayList<RepoModel>()
        val repoResponse = repoRepository.getUserReposByUsername(username, onErrorAction)
        for (response in repoResponse) {
            repoList.add(mapRepoResponseModelToRepoModel(response))
        }

        return repoList
    }

    private fun mapRepoResponseModelToRepoModel(repoResponseModel: RepoResponseModel): RepoModel {
        return RepoModel(
            repoResponseModel.name,
            repoResponseModel.htmlUrl,
            repoResponseModel.forksCount,
            repoResponseModel.starsCount
        )
    }
}