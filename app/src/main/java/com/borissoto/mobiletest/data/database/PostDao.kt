package com.borissoto.mobiletest.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("SELECT * FROM post")
    fun getAllPosts(): Flow<List<Post>>

    @Query("SELECT * FROM post WHERE id = :id")
    fun getPostById(id: Int): Flow<Post>

    @Query("SELECT COUNT(id) FROM post")
    suspend fun favoritePostCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<Post>)

    @Update
    suspend fun updatePost(post: Post)
}