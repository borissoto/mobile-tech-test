package com.borissoto.mobiletest.usecases

import com.borissoto.mobiletest.data.CommentsRepository
import javax.inject.Inject

class RequestCommentsUseCase @Inject constructor(
    private val commentsRepository: CommentsRepository
) {
    suspend operator fun invoke(postId: Int) = commentsRepository.getCommentsByPostId(postId)
}