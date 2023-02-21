package com.borissoto.mobiletest.data

import com.borissoto.mobiletest.data.datasource.IRemoteDataSource
import javax.inject.Inject

class CommentsRepository @Inject constructor(
    private val remoteDataSource: IRemoteDataSource
) {
    suspend fun getCommentsByPostId(postId: Int) =
        remoteDataSource.getCommentsByPostId(postId)
}