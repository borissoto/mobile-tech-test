package com.borissoto.mobiletest.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.borissoto.mobiletest.R
import com.borissoto.mobiletest.databinding.PostItemBinding
import com.borissoto.mobiletest.model.database.Post
import com.borissoto.mobiletest.util.basicDiffUtil
import com.borissoto.mobiletest.util.inflate

class MainAdapter(
    private val postClickedListener: (Post) -> Unit,
) : ListAdapter<Post, MainAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.post_item, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
        holder.itemView.setOnClickListener { postClickedListener(post)}
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = PostItemBinding.bind(view)
        fun bind(post: Post) {
            binding.post = post
        }
    }
}