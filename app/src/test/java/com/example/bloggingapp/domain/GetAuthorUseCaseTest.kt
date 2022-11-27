package com.example.bloggingapp.domain

import com.example.bloggingapp.data.repository.entity.Author
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetAuthorUseCaseTest {

    @Before
    fun setUp() {
        val author = Author(1, "Name", "user name", "email", "https://i.imgur.com/DvpvklR.png")

    }

    @After
    fun tearDown() {
    }

    @Test
    fun `invoke() when authorsRepository return author`(): Unit {

    }
}