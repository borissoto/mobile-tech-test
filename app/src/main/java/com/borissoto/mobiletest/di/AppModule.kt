package com.borissoto.mobiletest.di

import android.app.Application
import androidx.room.Room
import com.borissoto.mobiletest.framework.database.LocalDataSource
import com.borissoto.mobiletest.framework.database.PostDataBase
import com.borissoto.mobiletest.data.datasource.ILocalDataSource
import com.borissoto.mobiletest.data.datasource.IRemoteDataSource
import com.borissoto.mobiletest.framework.server.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        PostDataBase::class.java,
        "post-db"
    ).build()

    @Provides
    @Singleton
    fun providePostDao(db: PostDataBase) = db.postDao()

    @Provides
    fun provideRemoteDataSource(): IRemoteDataSource = RemoteDataSource()

}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule{
    @Binds
    abstract fun bindLocalDataSource(localDataSource: LocalDataSource): ILocalDataSource
}