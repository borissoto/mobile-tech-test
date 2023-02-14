package com.borissoto.mobiletest.data.datasource

import com.borissoto.mobiletest.domain.Author
import com.borissoto.mobiletest.domain.Comment
import com.borissoto.mobiletest.domain.Post

interface IRemoteDataSource {
    suspend fun getAllPosts(): List<Post>
    suspend fun getCommentsByPostId(postId: Int): List<Comment>
    suspend fun getAuthorByUserId(userId: Int): Author
}