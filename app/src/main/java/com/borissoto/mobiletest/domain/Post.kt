package com.borissoto.mobiletest.domain

data class Post(
    val id: Int,
    val body: String,
    val title: String,
    val userId: Int,
    val favorite: Boolean,
)