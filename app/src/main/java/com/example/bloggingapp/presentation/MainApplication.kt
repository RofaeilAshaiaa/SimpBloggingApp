package com.example.bloggingapp.presentation

import android.app.Application
import androidx.activity.viewModels
import com.example.bloggingapp.presentation.authors.AuthorPostsVM
import com.example.bloggingapp.presentation.authors.AuthorsVM
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application() {
}