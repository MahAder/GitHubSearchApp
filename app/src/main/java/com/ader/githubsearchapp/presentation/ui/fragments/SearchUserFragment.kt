package com.ader.githubsearchapp.presentation.ui.fragments

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ader.githubsearchapp.Constants
import com.ader.githubsearchapp.databinding.FragmentSearchUserBinding
import com.ader.githubsearchapp.domain.model.UserModel
import com.ader.githubsearchapp.presentation.model.UIUserModel
import com.ader.githubsearchapp.presentation.ui.MainActivity
import com.ader.githubsearchapp.presentation.ui.adapter.SearchUserAdapter
import com.ader.githubsearchapp.presentation.ui.navigation.ISearchUserFragmentNavigation
import com.ader.githubsearchapp.presentation.viewmodel.UserSearchViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_search_user.*
import java.util.*
import javax.inject.Inject

class SearchUserFragment : DaggerFragment(), ISearchUserFragmentNavigation {
    @Inject
    lateinit var viewModel: UserSearchViewModel

    var dataBinding: FragmentSearchUserBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentSearchUserBinding.inflate(inflater, container, false)
        viewModel.navigation = this
        dataBinding?.viewModel = viewModel
        dataBinding?.lifecycleOwner = this

        viewModel.errorLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })

        return dataBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUserList()
    }

    private fun initUserList() {
        val adapter = SearchUserAdapter()
        userList.adapter = adapter
        userList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter.onUserClick = {
            viewModel.navigateToUserDetails(it)
        }
    }

    object SearchUserFragmentBindingAdapter {
        @InverseBindingAdapter(attribute = "query")
        @JvmStatic
        fun getSearchQuery(editText: EditText): String {
            return editText.text.toString()
        }

        @BindingAdapter("query")
        @JvmStatic
        fun setQuery(editText: EditText, query: String?) {
            if (query == editText.text.toString()) return
            editText.setText(query ?: "")
        }

        @BindingAdapter("queryAttrChanged")
        @JvmStatic
        fun setOnQueryChangeListener(editText: EditText, attrChange: InverseBindingListener) {
            editText.addTextChangedListener(object : TextWatcher {
                private var timer = Timer()

                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    timer.cancel()
                    timer = Timer()
                    timer.schedule(object : TimerTask(){
                        override fun run() {
                            (editText.context as Activity).runOnUiThread {
                                attrChange.onChange()
                            }
                        }

                    }, Constants.SEARCH_DELAY)
                }
            })
        }

        @BindingAdapter("userList")
        @JvmStatic
        fun setUsers(recyclerView: RecyclerView, users: List<UIUserModel>?) {
            if (users == null) return
            (recyclerView.adapter as SearchUserAdapter).setData(users)
        }
    }

    override fun navigateToUserDetail(userModel: UserModel) {
        val action =
            SearchUserFragmentDirections.actionSearchUserFragmentToUserDetailsFragment(userModel)
        findNavController().navigate(action)
    }
}