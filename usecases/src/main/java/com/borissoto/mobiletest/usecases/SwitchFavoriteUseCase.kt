package com.borissoto.mobiletest.usecases

import com.borissoto.mobiletest.data.PostsRepository

class SwitchFavoriteUseCase(private val repository: PostsRepository) {

    suspend operator fun invoke(post: com.borissoto.mobiletest.domain.Post) = repository.switchFavorite(post)
}