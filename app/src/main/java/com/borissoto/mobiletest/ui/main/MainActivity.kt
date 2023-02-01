package com.borissoto.mobiletest.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.borissoto.mobiletest.data.database.PostsItem
import com.borissoto.mobiletest.databinding.ActivityMainBinding
import com.borissoto.mobiletest.domain.PostsRepository
import com.borissoto.mobiletest.ui.detail.DetailActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private val presenter by lazy { MainPresenter(PostsRepository(), lifecycleScope) }
    private var mainAdapter = MainAdapter{ navigateTo(it) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = mainAdapter
        presenter.onCreate(this)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun updateData(allPosts: List<PostsItem>) {
        binding.progressBarMain.visibility = View.VISIBLE
        mainAdapter.posts = allPosts
        mainAdapter.notifyDataSetChanged()
        binding.progressBarMain.visibility = View.GONE
    }

    override fun navigateTo(post: PostsItem) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_POST, post)
        startActivity(intent)
    }


}