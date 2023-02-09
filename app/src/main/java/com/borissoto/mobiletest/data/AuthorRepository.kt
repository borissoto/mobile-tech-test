package com.borissoto.mobiletest.data

import com.borissoto.mobiletest.framework.server.RemoteConnection

class AuthorRepository {
    suspend fun getAuthorById(userId: Int) = RemoteConnection.service.getUserById(userId)
}