package com.example.bloggingapp.data.repository

import com.example.bloggingapp.data.repository.entity.Author
import com.example.bloggingapp.data.repository.entity.Post
import com.example.bloggingapp.data.source.cache.AuthorCache
import com.example.bloggingapp.data.source.remote.AuthorRemoteSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorsRepository @Inject constructor(
    private val authorCache: AuthorCache,
    private val authorRemoteSource: AuthorRemoteSource
) {

    suspend fun getAllAuthors(): List<Author> {
        return try {
            val result = authorRemoteSource.getAll()
            if (result.isSuccessful) {
                (result.body() ?: arrayListOf()).apply {
                    authorCache.saveAllAuthors(this)
                }
            } else {
                authorCache.getAllAuthors()
            }
        } catch (e: Exception) {
            authorCache.getAllAuthors()
        }
    }

    suspend fun getAuthor(authorId: String): Author? {
        return try {
            val result = authorRemoteSource.getAuthor(authorId)
            if (result.isSuccessful) {
                val author = result.body()?.get(0)
                author
            } else {
                null
            }
        }catch (e:Exception){
            null
        }
    }

    suspend fun getAuthorPosts(authorId: String): List<Post> {
        try {
            val result = authorRemoteSource.getAuthorPosts(authorId)
            return if (result.isSuccessful) {
                val posts = result.body()
                if (posts?.isNotEmpty() == true) {
                    authorCache.savePosts(authorId, posts)
                    posts
                } else {
                    authorCache.getAllPosts(authorId)
                }
            } else {
                authorCache.getAllPosts(authorId)
            }
        }catch (e:Exception){
            return authorCache.getAllPosts(authorId)
        }
    }

}