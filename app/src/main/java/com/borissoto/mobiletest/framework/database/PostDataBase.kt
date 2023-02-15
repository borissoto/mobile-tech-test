package com.borissoto.mobiletest.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class PostDataBase: RoomDatabase() {

    abstract fun postDao(): PostDao
}