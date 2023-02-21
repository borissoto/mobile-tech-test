package com.borissoto.mobiletest.usecases

import com.borissoto.mobiletest.data.PostsRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val postsRepository: PostsRepository) {

    operator fun invoke() = postsRepository.allPosts
}