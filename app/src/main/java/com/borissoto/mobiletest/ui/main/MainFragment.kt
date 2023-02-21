package com.borissoto.mobiletest.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.borissoto.mobiletest.R
import com.borissoto.mobiletest.databinding.FragmentMainBinding
import com.borissoto.mobiletest.ui.util.app
import com.borissoto.mobiletest.ui.util.launchAndCollect
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    @Inject
    lateinit var vmFactory: MainViewModelFactory

    private val viewModel: MainViewModel by viewModels {
        vmFactory
    }

    private var mainAdapter = MainAdapter {
        mainState.onPostClicked(it)
    }
    private lateinit var mainState: MainState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app.component.inject(this)
    }

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