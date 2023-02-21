package com.borissoto.mobiletest.usecases

import com.borissoto.mobiletest.data.PostsRepository
import javax.inject.Inject

class RequestPostsUseCase @Inject constructor(private val postsRepository: PostsRepository) {

    suspend operator fun invoke() = postsRepository.requestAllPosts()
}