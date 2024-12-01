package com.sagayathri.presentation

import coil.util.CoilUtils.result
import com.sagayathri.data.async.DomainResult
import com.sagayathri.data.model.Joke
import com.sagayathri.data.usecase.GetJokeListUseCase
import com.sagayathri.presentation.jokeList.JokesListViewModel
import com.sagayathri.presentation.utils.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class JokesListViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewmodel: JokesListViewModel
    private val mockUseCase: GetJokeListUseCase = mock()

    @Before
    fun setUp() {
        viewmodel = JokesListViewModel(
            mockUseCase
        )
    }

    @Test
    fun `Given viewmodel, When initialised, Then state initial state`() {
        // Given
        // When
        val result = viewmodel.state.value

        // Then
        assertEquals(result.isLoading, false)
        assertEquals(result.items, emptyList<Joke>())
        assertEquals(result.isLoaded, false)
    }


    @Test
    fun `Given viewmodel, When on fetchJokes, Then fetch joke list`() = runTest {
        // Given
        val joke1 = mock<Joke> {
            on { id } doReturn 1
            on { type } doReturn "general"
            on { setup } doReturn "What did the fish say when it hit the wall?"
            on { punchline } doReturn "You put a little boogie on it."
        }

        val mockJokesResult: DomainResult.Success<List<Joke>> = mock {
            on { data } doReturn listOf(joke1)
        }
        whenever(mockUseCase.invoke(2)).thenReturn(mockJokesResult)

        // When
        viewmodel.fetchJokes(2)

        // Then
        assertNotNull(viewmodel.state)
        assertEquals(viewmodel.state.value.items.size, 1)
        assertEquals(viewmodel.state.value.items[0].id, 1)
        assertEquals(viewmodel.state.value.items[0].type, "general")
        assertEquals(viewmodel.state.value.items[0].setup, "What did the fish say when it hit the wall?")
        assertEquals(viewmodel.state.value.items[0].punchline, "You put a little boogie on it.")

    }
}