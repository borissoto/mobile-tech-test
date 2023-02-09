package com.borissoto.mobiletest.usecases

import com.borissoto.mobiletest.data.PostsRepository

class GetPostsUseCase(private val postsRepository: PostsRepository) {

    operator fun invoke() = postsRepository.allPosts
}