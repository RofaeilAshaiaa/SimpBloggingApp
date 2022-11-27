package com.example.bloggingapp.presentation.authors

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bloggingapp.data.repository.entity.Author
import com.example.bloggingapp.data.repository.entity.Post
import com.example.bloggingapp.domain.GetAuthorPostsUseCase
import com.example.bloggingapp.domain.GetAuthorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorPostsVM @Inject constructor(
    val getAuthorPostsUseCase: GetAuthorPostsUseCase,
    val getAuthorUseCase: GetAuthorUseCase,
) : ViewModel() {

    private val _authorPosts: MutableLiveData<List<Post>> = MutableLiveData()
    val authorPosts: LiveData<List<Post>> = _authorPosts

    private val _author: MutableLiveData<Author> = MutableLiveData()
    val author: LiveData<Author> = _author

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    private val loading: LiveData<Boolean> = _loading

    fun getAuthorPosts(authorId: String) {
        viewModelScope.launch {
            _loading.postValue(true)
            val posts = getAuthorPostsUseCase.invoke(authorId)
            _authorPosts.postValue(posts)
            _loading.postValue(false)
        }
    }

    fun getAuthorDetail(authorId: String) {
        viewModelScope.launch {
            _loading.postValue(true)
            val author = getAuthorUseCase.invoke(authorId)
            author?.let {
                _author.postValue(it)
            }
            _loading.postValue(false)
        }
    }

}