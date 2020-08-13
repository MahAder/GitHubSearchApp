package com.ader.githubsearchapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ader.githubsearchapp.R
import com.ader.githubsearchapp.domain.model.UserModel
import com.ader.githubsearchapp.presentation.model.UIUserModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_search_user.view.*

class SearchUserAdapter : RecyclerView.Adapter<SearchUserAdapter.UserSearchViewHolder>() {
    private val data = ArrayList<UIUserModel>()

    var onUserClick: (userModel: UIUserModel) -> Unit = {

    }

    fun setData(newData: List<UIUserModel>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSearchViewHolder {
        return UserSearchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_user, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: UserSearchViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class UserSearchViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(userModel: UIUserModel) {
            view.apply {
                Glide.with(view.context)
                    .load(userModel.getUserImgUrl())
                    .into(userIconIV)

                userNameTV.text = userModel.getUserName()
                repoCountTV.text = view.context.getString(
                    R.string.repo_format_string,
                    userModel.getUserRepoCount()
                )

                setOnClickListener {
                    onUserClick(userModel)
                }
            }
        }
    }
}