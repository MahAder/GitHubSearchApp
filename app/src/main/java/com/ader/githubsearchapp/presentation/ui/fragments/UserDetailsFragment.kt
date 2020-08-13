package com.ader.githubsearchapp.presentation.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ader.githubsearchapp.databinding.FragmentUserDetailsBinding
import com.ader.githubsearchapp.presentation.model.UIRepoModel
import com.ader.githubsearchapp.presentation.ui.adapter.RepositoryAdapter
import com.ader.githubsearchapp.presentation.viewmodel.UserDetailsViewModel
import com.bumptech.glide.Glide
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_user_details.*
import javax.inject.Inject


class UserDetailsFragment: DaggerFragment() {
    @Inject
    lateinit var viewModel: UserDetailsViewModel

    lateinit var dataBinding: FragmentUserDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = this

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    private fun initList(){
        val adapter = RepositoryAdapter()
        adapter.onRepoClick = {
            redirectToWeb(it.getRepoHtmlUrl())
        }
        repositoryList.adapter = adapter
        repositoryList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
    }

    private fun redirectToWeb(url: String){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    companion object UserDetailsBindingAdapter {
        @JvmStatic
        @BindingAdapter("imgUrl")
        fun setImgUrl(imageView: ImageView, imageUrl: String?){
            if(imageUrl == null) return
            Glide.with(imageView.context)
                .load(imageUrl)
                .into(imageView)
        }

        @JvmStatic
        @BindingAdapter("repoList")
        fun setRepoList(recyclerView: RecyclerView, repoList: List<UIRepoModel>?){
            if(repoList == null) return
            (recyclerView.adapter as RepositoryAdapter).setData(repoList)
        }
    }
}