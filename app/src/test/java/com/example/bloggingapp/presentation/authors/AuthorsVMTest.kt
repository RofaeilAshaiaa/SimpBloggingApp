package com.example.bloggingapp.presentation.authors

import com.example.bloggingapp.data.repository.entity.Author
import com.example.bloggingapp.domain.GetAuthorsUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AuthorsVMTest {

    private lateinit var viewModel: AuthorsVM

    @Mock
    lateinit var getAuthorsUseCase: GetAuthorsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        viewModel = AuthorsVM(getAuthorsUseCase)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `loadAuthors() return list of author, when getAuthorsUseCase returns data`() {
        runBlocking {
            //arrange
            val expected = emptyList<Author>()
            Mockito.`when`(getAuthorsUseCase.invoke()).thenReturn(expected)
            //act
            viewModel.loadAuthors(Dispatchers.Unconfined)
            // This works but can be improved.
            viewModel.authors.observeForever {
                //assert
                assertEquals(it, emptyList<Author>())
            }
        }

    }
}