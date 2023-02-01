package com.borissoto.mobiletest.domain

import com.borissoto.mobiletest.data.datasource.RemoteConnection

class AuthorRepository {
    suspend fun getAuthorById(userId: Int) = RemoteConnection.service.getUserById(userId)
}