package com.borissoto.mobiletest.domain

import com.borissoto.mobiletest.App
import com.borissoto.mobiletest.data.database.PostsItem
import com.borissoto.mobiletest.data.datasource.LocalDataSource
import com.borissoto.mobiletest.data.datasource.RemoteDataSource
import com.borissoto.mobiletest.model.database.Post

class PostsRepository(application: App) {

    private val localDataSource = LocalDataSource(application.db.postDao())
    private val remoteDataSource = RemoteDataSource()

    val allPosts = localDataSource.posts

    fun findPostById(id: Int) = localDataSource.findById(id)
    suspend fun requestAllPosts() {
        if (localDataSource.isEmpty()) {
            val posts = remoteDataSource.getAllPosts()
            localDataSource.save(posts.map { it.toLocalModel() })
        }
    }

    suspend fun switchFavorite(post: Post) {
        val updatedPost = post.copy(favorite = !post.favorite)
        localDataSource.save(listOf(updatedPost))
    }

}

private fun PostsItem.toLocalModel(): Post = Post(
    id,
    body,
    title,
    userId,
    false
)