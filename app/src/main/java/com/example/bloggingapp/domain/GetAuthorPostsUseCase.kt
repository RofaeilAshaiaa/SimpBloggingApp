package com.example.bloggingapp.domain

import com.example.bloggingapp.data.repository.AuthorsRepository
import com.example.bloggingapp.data.repository.entity.Post
import javax.inject.Inject

class GetAuthorPostsUseCase @Inject constructor(
    private val authorsRepository: AuthorsRepository
) {
    suspend operator fun invoke(authorId: String): List<Post> {
       return authorsRepository.getAuthorPosts(authorId)
    }
}