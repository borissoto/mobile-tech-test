package com.borissoto.mobiletest.di

import com.borissoto.mobiletest.data.AuthorRepository
import com.borissoto.mobiletest.data.CommentsRepository
import com.borissoto.mobiletest.data.PostsRepository
import com.borissoto.mobiletest.data.datasource.ILocalDataSource
import com.borissoto.mobiletest.data.datasource.IRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
object DataModule {

    @Provides
    fun providePostsRepository(
        localDataSource: ILocalDataSource,
        remoteDataSource: IRemoteDataSource
    ) = PostsRepository(localDataSource, remoteDataSource)

    @Provides
    fun provideCommentsRepository(
        remoteDataSource: IRemoteDataSource
    ) = CommentsRepository(remoteDataSource)

    @Provides
    fun provideAuthorRepository(
        remoteDataSource: IRemoteDataSource
    ) = AuthorRepository(remoteDataSource)
}