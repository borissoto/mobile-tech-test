package com.borissoto.mobiletest.ui.detail

import com.borissoto.mobiletest.usecases.FindPostUseCase
import com.borissoto.mobiletest.usecases.RequestAuthorUseCase
import com.borissoto.mobiletest.usecases.RequestCommentsUseCase
import com.borissoto.mobiletest.usecases.SwitchFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class DetailFragmentModule(private val postId: Int, private val userId: Int){

    @Provides
    fun provideDetailViewModelFactory(
        findPostUseCase: FindPostUseCase,
        switchFavoriteUseCase: SwitchFavoriteUseCase,
        requestCommentsUseCase: RequestCommentsUseCase,
        authorUseCase: RequestAuthorUseCase
    ) = DetailViewModelFactory(postId, userId, findPostUseCase, switchFavoriteUseCase, requestCommentsUseCase, authorUseCase)
}

@Subcomponent(modules = [DetailFragmentModule::class])
interface DetailFragmentComponent{
    val detailViewModelFactory: DetailViewModelFactory
}