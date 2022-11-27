package com.example.bloggingapp.data.repository.entity

import android.icu.text.CaseMap.Title
import retrofit2.http.Body
import java.util.StringJoiner

data class Post(
    val id:Int,
    val data:String,
    val title: String,
    val body: String,
    val imageUrl:String,
    val authorId :Int
)