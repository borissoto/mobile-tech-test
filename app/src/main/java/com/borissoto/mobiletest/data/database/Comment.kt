package com.borissoto.mobiletest.data.database

//class Comment : ArrayList<CommentItem>()

data class CommentItem(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)