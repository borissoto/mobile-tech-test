package com.borissoto.mobiletest.data

import com.borissoto.mobiletest.data.datasource.ILocalDataSource
import com.borissoto.mobiletest.data.datasource.IRemoteDataSource
import com.borissoto.mobiletest.domain.Post

class PostsRepository(
    private val localDataSource: ILocalDataSource,
    private val remoteDataSource: IRemoteDataSource
) {

    val allPosts = localDataSource.posts

    fun findPostById(id: Int) = localDataSource.findById(id)
    suspend fun requestAllPosts() {
        if (localDataSource.isEmpty()) {
            val posts = remoteDataSource.getAllPosts()
            localDataSource.save(posts)
        }
    }

    suspend fun switchFavorite(post: Post) {
        val updatedPost = post.copy(favorite = !post.favorite)
        localDataSource.save(listOf(updatedPost))
    }
}
