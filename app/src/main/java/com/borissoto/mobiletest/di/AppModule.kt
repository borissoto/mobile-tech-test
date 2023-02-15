package com.borissoto.mobiletest.di

import android.app.Application
import androidx.room.Room
import com.borissoto.mobiletest.framework.database.LocalDataSource
import com.borissoto.mobiletest.framework.database.PostDataBase
import com.borissoto.mobiletest.data.datasource.ILocalDataSource
import com.borissoto.mobiletest.data.datasource.IRemoteDataSource
import com.borissoto.mobiletest.framework.server.RemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        PostDataBase::class.java,
        "post-db"
    ).build()

    @Provides
    fun provideRemoteDataSource(): IRemoteDataSource =
        RemoteDataSource()

    @Provides
    fun provideLocalDataSource(db: PostDataBase): ILocalDataSource =
        LocalDataSource(db.postDao())
}