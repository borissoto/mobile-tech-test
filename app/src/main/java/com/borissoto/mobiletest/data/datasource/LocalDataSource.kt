package com.borissoto.mobiletest.data.datasource

import com.borissoto.mobiletest.model.database.Post
import com.borissoto.mobiletest.model.database.PostDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val postDao: PostDao) {
    suspend fun isEmpty(): Boolean = postDao.favoritePostCount() == 0

    fun findById(id: Int): Flow<Post> = postDao.getPostById(id)

    suspend fun save(posts: List<Post>) {
        postDao.insertPosts(posts)
    }

    val posts: Flow<List<Post>> = postDao.getAllPosts()


}