package com.borissoto.mobiletest.framework.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("SELECT * FROM post ORDER BY favorite DESC")
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