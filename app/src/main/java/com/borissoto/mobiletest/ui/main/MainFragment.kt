package com.borissoto.mobiletest.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.borissoto.mobiletest.R
import com.borissoto.mobiletest.databinding.FragmentMainBinding
import com.borissoto.mobiletest.usecases.GetPostsUseCase
import com.borissoto.mobiletest.data.PostsRepository
import com.borissoto.mobiletest.framework.database.LocalDataSource
import com.borissoto.mobiletest.framework.server.RemoteDataSource
import com.borissoto.mobiletest.usecases.RequestPostsUseCase
import com.borissoto.mobiletest.ui.util.app
import com.borissoto.mobiletest.ui.util.launchAndCollect

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels {
        val localDataSource = LocalDataSource(requireActivity().app.db.postDao())
        val remoteDataSource = RemoteDataSource()

        val repository = PostsRepository(localDataSource, remoteDataSource)
        MainViewModelFactory(
            GetPostsUseCase(repository),
            RequestPostsUseCase(repository)
        )
    }

    private var mainAdapter = MainAdapter {
        mainState.onPostClicked(it)
    }
    private lateinit var mainState: MainState

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainState = MainState(
            viewLifecycleOwner.lifecycleScope,
            findNavController()
        )

        val binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = mainAdapter
        }

        viewLifecycleOwner.launchAndCollect(viewModel.state) {
                    binding.loading = it.loading
                    binding.posts = it.posts
        }

        mainState.uiReady {
            viewModel.onUiReady()
        }
    }

}