package com.borissoto.mobiletest.framework.server

import com.borissoto.mobiletest.data.datasource.IRemoteDataSource
import com.borissoto.mobiletest.domain.Author
import com.borissoto.mobiletest.domain.Comment
import com.borissoto.mobiletest.domain.Post
import com.borissoto.mobiletest.framework.server.model.PostsItem

class RemoteDataSource() : IRemoteDataSource {
    override suspend fun getAllPosts() = RemoteConnection.service.listAllPosts().map {
        it.toLocalModel()
    }

    override suspend fun getCommentsByPostId(postId: Int): List<Comment> =
        RemoteConnection.service.getCommentsByPostId(postId).map {
            it
        }

    override suspend fun getAuthorByUserId(userId: Int): Author =
        RemoteConnection.service.getUserById(userId)
}

private fun PostsItem.toLocalModel(): Post =
    Post(
        id,
        body,
        title,
        userId,
        false
    )
