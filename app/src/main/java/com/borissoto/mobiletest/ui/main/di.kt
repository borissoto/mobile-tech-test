package com.borissoto.mobiletest.ui.main

import com.borissoto.mobiletest.usecases.GetPostsUseCase
import com.borissoto.mobiletest.usecases.RequestPostsUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class MainFragmentModule(){

    @Provides
    fun provideMainViewModelFactory(
        getPostsUseCase: GetPostsUseCase,
        requestPostsUseCase: RequestPostsUseCase
    ) = MainViewModelFactory(getPostsUseCase, requestPostsUseCase)
}

@Subcomponent(modules = [MainFragmentModule::class])
interface MainFragmentComponent{
    val mainViewModelFactory: MainViewModelFactory
}