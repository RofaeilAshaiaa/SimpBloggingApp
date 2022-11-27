package com.example.bloggingapp.data.source.remote

import com.example.bloggingapp.data.repository.entity.Author
import com.example.bloggingapp.data.repository.entity.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthorService {

    @GET("authors")
    suspend fun getAll(): Response<List<Author>>

    @GET("posts")
    suspend fun getAuthorPosts(@Query("authorId") authorId: String): Response<List<Post>>

    @GET("authors")
    suspend fun getAuthor(@Query("id") authorId: String): Response<List<Author>>
}