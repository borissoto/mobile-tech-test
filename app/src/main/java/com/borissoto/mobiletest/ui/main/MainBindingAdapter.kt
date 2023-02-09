package com.borissoto.mobiletest.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.borissoto.mobiletest.domain.Post

@BindingAdapter("items")
fun RecyclerView.setItems(posts: List<Post>?) {
    if (posts != null) {
        (adapter as? MainAdapter)?.submitList(posts)
    }
}