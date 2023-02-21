package com.borissoto.mobiletest.data

import com.borissoto.mobiletest.data.datasource.IRemoteDataSource
import javax.inject.Inject

class AuthorRepository @Inject constructor(
    private val remoteDataSource: IRemoteDataSource
) {
    suspend fun getAuthorById(userId: Int) =
        remoteDataSource.getAuthorByUserId(userId)
}