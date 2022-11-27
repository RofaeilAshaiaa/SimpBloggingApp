package com.example.bloggingapp.domain

import com.example.bloggingapp.data.repository.AuthorsRepository
import com.example.bloggingapp.data.repository.entity.Author
import javax.inject.Inject

class GetAuthorsUseCase @Inject constructor (
    private val authorsRepository: AuthorsRepository
) {
    suspend operator fun invoke(): List<Author> {
        return authorsRepository.getAllAuthors()
    }
}