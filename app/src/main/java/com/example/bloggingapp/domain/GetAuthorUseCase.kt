package com.example.bloggingapp.domain

import com.example.bloggingapp.data.repository.AuthorsRepository
import com.example.bloggingapp.data.repository.entity.Author
import javax.inject.Inject

class GetAuthorUseCase @Inject constructor (
    private val authorsRepository: AuthorsRepository
) {
    suspend operator fun invoke(authorId: String): Author? {
        return authorsRepository.getAuthor(authorId)
    }
}