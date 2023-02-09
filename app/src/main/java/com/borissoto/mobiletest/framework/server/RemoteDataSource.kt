package com.borissoto.mobiletest.framework.server

import com.borissoto.mobiletest.data.datasource.IRemoteDataSource

class RemoteDataSource() : IRemoteDataSource {
    override suspend fun getAllPosts() = RemoteConnection.service.listAllPosts()
}