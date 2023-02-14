package com.borissoto.mobiletest

import android.app.Application
import androidx.room.Room
import com.borissoto.mobiletest.data.database.PostDataBase

class App : Application() {

    lateinit var db: PostDataBase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(this, PostDataBase::class.java, "post-db").build()
    }
}