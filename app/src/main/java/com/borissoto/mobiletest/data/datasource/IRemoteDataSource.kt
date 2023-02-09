package com.borissoto.mobiletest.data.datasource

import com.borissoto.mobiletest.framework.server.model.PostsItem

interface IRemoteDataSource {
    suspend fun getAllPosts(): List<PostsItem>
}