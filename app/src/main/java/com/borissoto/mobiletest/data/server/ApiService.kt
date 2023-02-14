package com.borissoto.mobiletest.data.server

import com.borissoto.mobiletest.domain.Author
import com.borissoto.mobiletest.domain.Comment
import com.borissoto.mobiletest.data.server.model.PostsItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("posts")
    suspend fun listAllPosts(): List<PostsItem>

    @GET("users/{user_id}")
    suspend fun getUserById(@Path(value = "user_id") userId: Int): Author

    @GET("posts/{post_id}/comments")
    suspend fun getCommentsByPostId(@Path(value = "post_id") postId: Int): List<Comment>
}