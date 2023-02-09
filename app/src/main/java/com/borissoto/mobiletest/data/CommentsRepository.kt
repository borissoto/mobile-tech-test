package com.borissoto.mobiletest.data

import com.borissoto.mobiletest.framework.server.RemoteConnection

class CommentsRepository {
    suspend fun getCommentsByPostId(postId: Int) =
        RemoteConnection.service.getCommentsByPostId(postId)
}