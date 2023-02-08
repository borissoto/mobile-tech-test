package com.borissoto.mobiletest.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.borissoto.mobiletest.model.database.Post

@BindingAdapter("items")
fun RecyclerView.setItems(posts: List<Post>?) {
    if (posts != null) {
        (adapter as? MainAdapter)?.submitList(posts)
    }
}