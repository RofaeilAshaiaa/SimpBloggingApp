package com.example.bloggingapp.data.source.remote

import com.example.bloggingapp.data.repository.entity.Author
import com.example.bloggingapp.data.repository.entity.Post
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorRemoteSource @Inject constructor(private val userService: AuthorService) {

    suspend fun getAll(): Response<List<Author>> {
        return userService.getAll()
    }

    suspend fun getAuthorPosts(authorId: String): Response<List<Post>> {
        return userService.getAuthorPosts(authorId)
    }

    suspend fun getAuthor(authorId: String): Response<List<Author>> {
        return userService.getAuthor(authorId)
    }
}