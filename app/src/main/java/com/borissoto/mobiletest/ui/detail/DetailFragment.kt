package com.borissoto.mobiletest.ui.detail

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.borissoto.mobiletest.R
import com.borissoto.mobiletest.databinding.FragmentDetailBinding
import com.borissoto.mobiletest.domain.Author
import com.borissoto.mobiletest.ui.util.app
import com.borissoto.mobiletest.ui.util.launchAndCollect


class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val safeArgs: DetailFragmentArgs by navArgs()

    private lateinit var component: DetailFragmentComponent

    private val viewModel: DetailViewModel by viewModels {
        component.detailViewModelFactory
    }

    private var detailAdapter = DetailAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = app.component.plus(DetailFragmentModule(safeArgs.postId, safeArgs.userId))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)
        viewLifecycleOwner.launchAndCollect(viewModel.state) {
            if (it.post != null) {
                binding.post = it.post
            }
        }

        viewModel.stateAuthor.observe(requireActivity()) {
            binding.updateAuthor(it)
        }

        viewModel.stateComments.observe(requireActivity()) {
            binding.updateComments(it)
        }

        binding.recyclerComments.adapter = detailAdapter
        binding.btnFavorite.setOnClickListener {
            viewModel.onFavoriteClicked()
        }
    }

    private fun FragmentDetailBinding.updateAuthor(state: DetailViewModel.UiAuthorState) {
        progressBarAuthor.visibility = if (state.loading) View.VISIBLE else View.GONE
        state.author?.let {
            bindDetailInfoAuthor(textAuthorName, it)
        }
    }

    private fun FragmentDetailBinding.updateComments(state: DetailViewModel.UiCommentsState) {

        progressBarComments.visibility = if (state.loading) View.VISIBLE else View.GONE
        state.comments?.let {
            detailAdapter.comments = it
        }
    }


    private fun bindDetailInfoAuthor(detailInfo: TextView, author: Author) {
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