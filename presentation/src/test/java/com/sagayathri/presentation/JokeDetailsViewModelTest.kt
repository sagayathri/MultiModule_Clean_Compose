package com.sagayathri.presentation

import com.sagayathri.data.async.DomainResult
import com.sagayathri.data.model.Joke
import com.sagayathri.data.usecase.GetJokeByIdUseCase
import com.sagayathri.presentation.jokeDetail.JokeDetailsViewModel
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

class JokeDetailsViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewmodel: JokeDetailsViewModel
    private val mockUseCase: GetJokeByIdUseCase = mock()

    @Before
    fun setUp() {
        viewmodel = JokeDetailsViewModel(
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
        assertEquals(result.item, null)
        assertEquals(result.isLoaded, false)
    }


    @Test
    fun `Given viewmodel, When on fetchJokeByID, Then fetch joke list`() = runTest {
        // Given
        val joke = mock<Joke> {
            on { id } doReturn 110
            on { type } doReturn "general"
            on { setup } doReturn "Have you heard of the band 1023MB?"
            on { punchline } doReturn "They haven't got a gig yet."
        }

        val mockJokesResult: DomainResult.Success<Joke> = mock {
            on { data } doReturn joke
        }
        whenever(mockUseCase.invoke(110)).thenReturn(mockJokesResult)

        // When
        viewmodel.fetchJokeByID(110)

        // Then
        assertNotNull(viewmodel.state.value.item)
        assertEquals(viewmodel.state.value.item?.id, 110)
        assertEquals(viewmodel.state.value.item?.type, "general")
        assertEquals(viewmodel.state.value.item?.setup, "Have you heard of the band 1023MB?")
        assertEquals(viewmodel.state.value.item?.punchline, "They haven't got a gig yet.")
    }
}