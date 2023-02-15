package com.borissoto.mobiletest.di

import com.borissoto.mobiletest.ui.main.MainViewModelFactory
import com.borissoto.mobiletest.usecases.*
import dagger.Module
import dagger.Provides

@Module
object ViewModelsModule {

    @Provides
    fun provideMainViewModelFactory(
        getPostsUseCase: GetPostsUseCase,
        requestPostsUseCase: RequestPostsUseCase
    ) = MainViewModelFactory(
        getPostsUseCase, requestPostsUseCase
    )

}