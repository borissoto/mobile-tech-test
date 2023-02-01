package com.borissoto.mobiletest.domain

import com.borissoto.mobiletest.data.datasource.RemoteConnection

class CommentsRepository {
    suspend fun getCommentsByPostId(postId: Int) =
        RemoteConnection.service.getCommentsByPostId(postId)
}