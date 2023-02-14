package com.borissoto.mobiletest.usecases

import com.borissoto.mobiletest.data.CommentsRepository

class RequestCommentsUseCase(
    private val commentsRepository: CommentsRepository
) {
    suspend operator fun invoke(postId: Int) = commentsRepository.getCommentsByPostId(postId)
}