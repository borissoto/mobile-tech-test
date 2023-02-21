package com.borissoto.mobiletest.usecases

import com.borissoto.mobiletest.data.PostsRepository
import javax.inject.Inject

class FindPostUseCase @Inject constructor(private val repository: PostsRepository) {

    operator fun invoke(postId: Int) = repository.findPostById(postId)
}