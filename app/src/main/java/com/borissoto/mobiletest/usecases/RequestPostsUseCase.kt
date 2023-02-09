package com.borissoto.mobiletest.usecases

import com.borissoto.mobiletest.data.PostsRepository

class RequestPostsUseCase(private val postsRepository: PostsRepository) {

    suspend operator fun invoke() = postsRepository.requestAllPosts()
}