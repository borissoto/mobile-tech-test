package com.borissoto.mobiletest.di

import com.borissoto.mobiletest.data.AuthorRepository
import com.borissoto.mobiletest.data.CommentsRepository
import com.borissoto.mobiletest.data.PostsRepository
import com.borissoto.mobiletest.usecases.*
import dagger.Module
import dagger.Provides

@Module
object UseCaseModule {

    @Provides
    fun provideGetPostsUseCase(postsRepository: PostsRepository) =
        GetPostsUseCase(postsRepository)

    @Provides
    fun provideRequestPostsUseCase(postsRepository: PostsRepository) =
        RequestPostsUseCase(postsRepository)

    @Provides
    fun provideFindPostUseCase(postsRepository: PostsRepository) =
        FindPostUseCase(postsRepository)

    @Provides
    fun provideSwitchFavoriteUseCase(postsRepository: PostsRepository) =
        SwitchFavoriteUseCase(postsRepository)

    @Provides
    fun provideRequestAuthorUseCase(authorRepository: AuthorRepository) =
        RequestAuthorUseCase(authorRepository)

    @Provides
    fun provideRequestCommentsUseCase(commentsRepository: CommentsRepository) =
        RequestCommentsUseCase(commentsRepository)
}