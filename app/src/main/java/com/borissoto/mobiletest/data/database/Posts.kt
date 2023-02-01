package com.borissoto.mobiletest.data.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//class Posts : ArrayList<PostsItem>()
@Parcelize
data class PostsItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
): Parcelable