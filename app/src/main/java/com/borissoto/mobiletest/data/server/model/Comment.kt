package com.borissoto.mobiletest.data.server.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//class Comment : ArrayList<CommentItem>()

@Parcelize
data class CommentItem(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
): Parcelable