package com.borissoto.mobiletest.usecases

import com.borissoto.mobiletest.data.AuthorRepository
import javax.inject.Inject

class RequestAuthorUseCase @Inject constructor(
    private val authorRepository: AuthorRepository
) {
    suspend operator fun invoke(userId: Int) = authorRepository.getAuthorById(userId)
}