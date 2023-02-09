package com.borissoto.mobiletest.usecases

import com.borissoto.mobiletest.data.PostsRepository

class FindPostUseCase(private val repository: PostsRepository) {

    operator fun invoke(postId: Int) = repository.findPostById(postId)
}