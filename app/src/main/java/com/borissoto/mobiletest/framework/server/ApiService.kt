package com.borissoto.mobiletest.framework.server

import com.borissoto.mobiletest.framework.server.model.CommentItem
import com.borissoto.mobiletest.framework.server.model.PostsItem
import com.borissoto.mobiletest.framework.server.model.UserItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("posts")
    suspend fun listAllPosts(): List<PostsItem>

    @GET("users/{user_id}")
    suspend fun getUserById(@Path(value = "user_id") userId: Int): UserItem

    @GET("posts/{post_id}/comments")
    suspend fun getCommentsByPostId(@Path(value = "post_id") postId: Int): List<CommentItem>
}