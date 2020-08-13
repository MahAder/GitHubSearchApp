package com.ader.githubsearchapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ader.githubsearchapp.R
import com.ader.githubsearchapp.presentation.model.UIRepoModel
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryAdapter: RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
    private val data = ArrayList<UIRepoModel>()

    var onRepoClick: (repoModel : UIRepoModel) -> Unit = {

    }

    fun setData(repos: List<UIRepoModel>){
        data.clear()
        data.addAll(repos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class RepositoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(repoModel: UIRepoModel) {
            view.apply {
                repositoryNameTV.text = repoModel.getRepoName()

                forksCountTV.text = view.context.getString(
                    R.string.forks_format_string,
                    repoModel.getRepoForksCount()
                )
                starsCountTV.text = view.context.getString(
                    R.string.stars_format_string,
                    repoModel.getRepoStarsCount()
                )

                setOnClickListener {
                    onRepoClick(repoModel)
                }
            }
        }
    }
}