package com.borissoto.mobiletest.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.borissoto.mobiletest.R
import com.borissoto.mobiletest.data.database.PostsItem
import com.borissoto.mobiletest.data.database.UserItem
import com.borissoto.mobiletest.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_POST = "DetailActivity:post"
    }

    private val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(
            requireNotNull(
                intent.getParcelableExtra(EXTRA_POST)
            )
        )
    }

    private var detailAdapter = DetailAdapter()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.state.observe(this) {
            updateUI(it.post)
        }

        viewModel.stateAuthor.observe(this){
           updateAuthor(it)
        }

        viewModel.stateComments.observe(this){
            updateComments(it)
        }

        binding.recyclerComments.adapter = detailAdapter
        binding.btnFavorite.setOnClickListener {

        }
    }

    private fun updateUI(post: PostsItem) = with(binding) {
            textPostTitle.text = post.title
            textPostBody.text = post.body
    }

    private fun updateAuthor(state: DetailViewModel.UiAuthorState) {
        binding.progressBarAuthor.visibility = if (state.loading) View.VISIBLE else View.GONE
        state.author?.let {
            bindDetailInfoAuthor(binding.textAuthorName, it)
        }
    }

    private fun updateComments(state: DetailViewModel.UiCommentsState) {

        binding.progressBarComments.visibility = if (state.loading) View.VISIBLE else View.GONE
        state.comments?.let {
            detailAdapter.comments = it
        }
    }


    private fun bindDetailInfoAuthor(detailInfo: TextView, author: UserItem) {
        detailInfo.text = buildSpannedString {
            appendInfo(R.string.author_name, author.name)
            appendInfo(R.string.author_username, author.username)
            appendInfo(R.string.author_email, author.email)
            appendInfo(R.string.author_website, author.website)

        }
    }

    private fun SpannableStringBuilder.appendInfo(stringRes: Int, value: String) {
        bold {
            append(getString(stringRes))
            append(": ")
        }
        appendLine(value)
    }

}