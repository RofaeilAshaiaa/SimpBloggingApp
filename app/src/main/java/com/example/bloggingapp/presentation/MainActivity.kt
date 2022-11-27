package com.example.bloggingapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bloggingapp.presentation.authors.AuthorPostsVM
import com.example.bloggingapp.presentation.authors.AuthorsVM
import com.example.bloggingapp.presentation.authors.ui.AuthorDetailsScreen
import com.example.bloggingapp.presentation.authors.ui.AuthorsScreen
import com.example.bloggingapp.presentation.theme.BloggingAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val authorsVM: AuthorsVM by viewModels()
    val authorPostsVM: AuthorPostsVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            BloggingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    authorsVM.loadAuthors()
                    NavHost(navController = navController, startDestination = "authorList") {
                        composable("authorDetails/{authorId}") { backStackEntry ->
                            AuthorDetailsScreen(authorPostsVM, backStackEntry.arguments?.getString("authorId"))

                        }
                        composable("authorList") { AuthorsScreen(authorsVM, navController) }
                    }
                }
            }
        }
    }

}
