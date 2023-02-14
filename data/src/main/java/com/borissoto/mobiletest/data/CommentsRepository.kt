package com.borissoto.mobiletest.data

import com.borissoto.mobiletest.data.datasource.IRemoteDataSource

class CommentsRepository(
    private val remoteDataSource: IRemoteDataSource
) {
    suspend fun getCommentsByPostId(postId: Int) =
        remoteDataSource.getCommentsByPostId(postId)
}