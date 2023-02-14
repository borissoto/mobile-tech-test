package com.borissoto.mobiletest.data

import com.borissoto.mobiletest.data.datasource.IRemoteDataSource

class AuthorRepository(
    private val remoteDataSource: IRemoteDataSource
) {
    suspend fun getAuthorById(userId: Int) =
        remoteDataSource.getAuthorByUserId(userId)
}