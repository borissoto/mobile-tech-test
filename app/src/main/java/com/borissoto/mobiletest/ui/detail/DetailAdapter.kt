package com.borissoto.mobiletest.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.borissoto.mobiletest.databinding.CommentItemBinding
import com.borissoto.mobiletest.domain.Comment
import kotlin.properties.Delegates

class DetailAdapter() : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {
    var comments: List<Comment> by Delegates.observable(emptyList()) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition].id == new[newItemPosition].id

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition] == new[newItemPosition]
        }).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CommentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = comments.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comments = comments[position]
        holder.bind(comments)
    }

    class ViewHolder(private val binding: CommentItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment){
            binding.textComment.text = comment.body
            binding.textCommentEmail.text = comment.email
        }
    }
}