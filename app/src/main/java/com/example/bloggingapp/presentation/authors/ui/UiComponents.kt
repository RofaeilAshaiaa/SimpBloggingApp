package com.example.bloggingapp.presentation.authors.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asFlow
import androidx.navigation.NavHostController
import com.example.bloggingapp.data.repository.entity.Author
import com.example.bloggingapp.data.repository.entity.Post
import com.example.bloggingapp.presentation.authors.AuthorPostsVM
import com.example.bloggingapp.presentation.authors.AuthorsVM
import com.example.bloggingapp.presentation.theme.BloggingAppTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun AuthorDetailsScreen(authorPostsVM: AuthorPostsVM, authorId: String?) {
    authorId?.let {
        authorPostsVM.getAuthorPosts(it)
        authorPostsVM.getAuthorDetail(it)
    }
    val posts: List<Post> by authorPostsVM.authorPosts.asFlow().collectAsState(emptyList())
    val author: Author by authorPostsVM.author.asFlow().collectAsState(Author(0, "", "", "", ""))
    Column {
        if (author.id != 0) {
            Row {
                AuthorCard(null, author)
            }
        }
        Row {
            LazyColumn(
                contentPadding = PaddingValues(bottom = 8.dp),
            ) {
                itemsIndexed(posts) { groupIndex, author ->
                    PostCard(author)
                }
            }
        }
    }
}

@Composable
fun AuthorsScreen(
    authorsVM: AuthorsVM, navController: NavHostController,
) {
    val authors: List<Author> by authorsVM.authors.asFlow().collectAsState(emptyList())
    LazyColumn(
        contentPadding = PaddingValues(bottom = 8.dp),
    ) {
        itemsIndexed(authors) { groupIndex, author ->
            AuthorCard(navController, author)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun AuthorCard(
    navController: NavHostController?,
    item: Author
) {
    Card(
        modifier = Modifier
            .height(100.dp)
            .padding(top = 8.dp, start = 8.dp, end = 8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp,
        onClick = {
            navController?.navigate("authorDetails/${item.id}")
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                val imageUrl = item.avatarUrl
                GlideImage(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .fillMaxSize(),
                    imageModel = { imageUrl }, // loading a network image using an URL.
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    )
                )
            }
            Column(
                modifier = Modifier
                    .weight(4f, true)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
private fun PostCard(
    item: Post
) {
    Card(
        modifier = Modifier
            .height(150.dp)
            .padding(top = 8.dp, start = 8.dp, end = 8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                val imageUrl = item.imageUrl
                GlideImage(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .fillMaxSize(),
                    imageModel = { imageUrl }, // loading a network image using an URL.
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    )
                )
            }
            Column(
                modifier = Modifier
                    .weight(4f, true)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Row {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )
                }
                Row {
                    Text(
                        text = item.body,
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthorsScreenPreview() {
    BloggingAppTheme {
        //AuthorsScreen(authorsVM)
    }
}

@Preview(showBackground = true)
@Composable
fun AuthorDetailScreenPreview() {
    BloggingAppTheme {
        //AuthorDetailsScreen(authorPostsVM)
    }
}