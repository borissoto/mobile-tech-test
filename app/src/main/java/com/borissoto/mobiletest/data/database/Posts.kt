package com.borissoto.mobiletest.data.database

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//class Posts : ArrayList<PostsItem>()
@Parcelize
data class PostsItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
): Parcelable