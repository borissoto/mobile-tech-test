package com.borissoto.mobiletest.data.datasource

class RemoteDataSource() {
    suspend fun getAllPosts() = RemoteConnection.service.listAllPosts()
}