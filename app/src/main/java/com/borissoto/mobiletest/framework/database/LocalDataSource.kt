package com.borissoto.mobiletest.framework.database

import com.borissoto.mobiletest.data.datasource.ILocalDataSource
import com.borissoto.mobiletest.framework.database.Post as DBPost
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class LocalDataSource @Inject constructor(private val postDao: PostDao) : ILocalDataSource {

    override val posts: Flow<List<com.borissoto.mobiletest.domain.Post>> = postDao.getAllPosts().map {
        it.map { post ->
            post.toDomainModel()
        }
    }
    override suspend fun isEmpty(): Boolean = postDao.favoritePostCount() == 0

    override fun findById(id: Int): Flow<com.borissoto.mobiletest.domain.Post> = postDao.getPostById(id).map {
        it.toDomainModel()
    }

    override suspend fun save(posts: List<com.borissoto.mobiletest.domain.Post>) {
        postDao.insertPosts(posts.map {
            it.fromDomainModel()
        })
    }
}


private fun DBPost.toDomainModel(): com.borissoto.mobiletest.domain.Post =
    com.borissoto.mobiletest.domain.Post(
        id, body, title, userId, favorite
    )

private fun com.borissoto.mobiletest.domain.Post.fromDomainModel(): DBPost = DBPost(
    id, body, title, userId, favorite
)