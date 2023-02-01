package com.borissoto.mobiletest.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.borissoto.mobiletest.data.database.PostsItem
import com.borissoto.mobiletest.databinding.ActivityMainBinding
import com.borissoto.mobiletest.domain.PostsRepository
import com.borissoto.mobiletest.ui.detail.DetailActivity

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(PostsRepository())  }

    private var mainAdapter = MainAdapter{ viewModel.onPostClicked(it) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = mainAdapter

        viewModel.state.observe(this){
            updateUi(it)
        }
    }

    private fun updateUi(state: MainViewModel.UiState) {
        binding.progressBarMain.visibility = if (state.loading) View.VISIBLE else View.GONE
        state.posts?.let {
            mainAdapter.posts = it
        }
        state.navigateTo?.let {
            navigateTo(it)
        }
    }

    private fun navigateTo(post: PostsItem) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_POST, post)
        startActivity(intent)
    }
}