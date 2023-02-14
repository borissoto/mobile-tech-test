package com.borissoto.mobiletest.data.datasource

import com.borissoto.mobiletest.domain.Post
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {
    val posts: Flow<List<Post>>

    suspend fun isEmpty(): Boolean
    fun findById(id: Int): Flow<Post>
    suspend fun save(posts: List<Post>)
}