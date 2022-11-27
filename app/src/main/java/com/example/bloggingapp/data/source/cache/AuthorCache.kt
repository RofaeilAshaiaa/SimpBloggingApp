package com.example.bloggingapp.data.source.cache

import android.content.Context
import com.example.bloggingapp.data.repository.entity.Author
import com.example.bloggingapp.data.repository.entity.Post
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorCache @Inject constructor(@ApplicationContext val context: Context) {

    private val sharedPref = context.getSharedPreferences(
        AuthorCache::class.java.simpleName,
        Context.MODE_PRIVATE
    )

    private val gson = Gson()

    private object Key {
        val keyAuthors = "KEY_ALL_AUTHOR"
    }

    fun saveAllAuthors(author: List<Author>) {
        sharedPref.edit().putString(Key.keyAuthors, gson.toJson(author)).apply()
    }

    fun getAllAuthors(): List<Author> {
        val json = sharedPref.getString(Key.keyAuthors, "") ?: ""
        return if (json.isNotBlank()) {
            val type = object : TypeToken<List<Author>>() {}.type
            gson.fromJson(json, type)
        } else {
            arrayListOf()
        }
    }

    fun savePosts(authorId: String, posts: List<Post>) {
        sharedPref.edit().putString("POST$authorId", gson.toJson(posts)).apply()
    }

    fun getAllPosts(authorId: String): List<Post> {
        val json = sharedPref.getString("POST$authorId", "") ?: ""
        return if (json.isNotBlank()) {
            val type = object : TypeToken<List<Post>>() {}.type
            gson.fromJson(json, type)
        } else {
            arrayListOf()
        }
    }

}