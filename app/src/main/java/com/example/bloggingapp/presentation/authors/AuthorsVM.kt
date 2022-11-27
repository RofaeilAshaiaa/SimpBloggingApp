package com.example.bloggingapp.presentation.authors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bloggingapp.data.repository.entity.Author
import com.example.bloggingapp.domain.GetAuthorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorsVM @Inject constructor(
    val getAuthorsUseCase: GetAuthorsUseCase,
) : ViewModel() {

    private val _authors: MutableLiveData<List<Author>> = MutableLiveData()
    val authors: LiveData<List<Author>> = _authors

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    fun loadAuthors(dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        viewModelScope.launch(dispatcher) {
            _loading.postValue(true)
            val authors = getAuthorsUseCase.invoke()
            _authors.postValue(authors)
            _loading.postValue(false)
        }
    }

}