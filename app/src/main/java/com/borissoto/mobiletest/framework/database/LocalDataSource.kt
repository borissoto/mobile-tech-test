package com.borissoto.mobiletest.framework.database

import com.borissoto.mobiletest.data.datasource.ILocalDataSource
import com.borissoto.mobiletest.domain.Post
import com.borissoto.mobiletest.framework.database.Post as DBPost
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map



class LocalDataSource(private val postDao: PostDao) : ILocalDataSource {

    override val posts: Flow<List<Post>> = postDao.getAllPosts().map {
        it.map { post ->
            post.toDomainModel()
        }
    }
    override suspend fun isEmpty(): Boolean = postDao.favoritePostCount() == 0

    override fun findById(id: Int): Flow<Post> = postDao.getPostById(id).map {
        it.toDomainModel()
    }

    override suspend fun save(posts: List<Post>) {
        postDao.insertPosts(posts.map {
            it.fromDomainModel()
        })
    }
}


private fun DBPost.toDomainModel(): Post = Post(
    id, body, title, userId, favorite
)

private fun Post.fromDomainModel(): DBPost = DBPost(
    id, body, title, userId, favorite
)