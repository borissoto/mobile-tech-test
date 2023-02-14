package com.borissoto.mobiletest.usecases

import com.borissoto.mobiletest.data.AuthorRepository

class RequestAuthorUseCase(
    private val authorRepository: AuthorRepository
) {
    suspend operator fun invoke(userId: Int) = authorRepository.getAuthorById(userId)
}