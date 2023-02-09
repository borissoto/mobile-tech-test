package com.borissoto.mobiletest.usecases

import com.borissoto.mobiletest.data.PostsRepository
import com.borissoto.mobiletest.domain.Post

class SwitchFavoriteUseCase(private val repository: PostsRepository) {

    suspend operator fun invoke(post: Post) = repository.switchFavorite(post)
}