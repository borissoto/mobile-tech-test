package com.borissoto.mobiletest.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.lifecycle.lifecycleScope
import com.borissoto.mobiletest.R
import com.borissoto.mobiletest.data.database.CommentItem
import com.borissoto.mobiletest.data.database.PostsItem
import com.borissoto.mobiletest.data.database.UserItem
import com.borissoto.mobiletest.databinding.ActivityDetailBinding
import com.borissoto.mobiletest.domain.AuthorRepository
import com.borissoto.mobiletest.domain.CommentsRepository


class DetailActivity : AppCompatActivity(), DetailPresenter.View {

    private val presenter by lazy { DetailPresenter(AuthorRepository(), CommentsRepository(), lifecycleScope) }
    private var detailAdapter = DetailAdapter()
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_POST = "DetailActivity:post"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = intent.getParcelableExtra<PostsItem>(EXTRA_POST)
        if (post != null) {
            binding.textPostTitle.text = post.title
            binding.textPostBody.text = post.body

            presenter.onCreate(this, post.id, post.userId)

            binding.recyclerComments.adapter = detailAdapter

            binding.btnFavorite.setOnClickListener {

            }
        }
    }

    override fun updateAuthor(author: UserItem) {
        binding.progressBarAuthor.visibility = View.VISIBLE
        bindDetailInfoAuthor(binding.textAuthorName, author)
        binding.progressBarAuthor.visibility = View.GONE
    }

    override fun updateComments(allComments: List<CommentItem>) {
        binding.progressBarComments.visibility = View.VISIBLE
        detailAdapter.comments = allComments
        detailAdapter.notifyDataSetChanged()
        binding.progressBarComments.visibility = View.GONE
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