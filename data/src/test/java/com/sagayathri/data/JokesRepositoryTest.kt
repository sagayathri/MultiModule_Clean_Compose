package com.sagayathri.data

import com.sagayathri.network.api.ApiService
import com.sagayathri.network.model.JokeEntity
import com.sagayathri.network.repository.JokesRepository
import com.sagayathri.network.repository.impl.JokesRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class JokesRepositoryTest {

    private lateinit var jokeRepository: JokesRepository

    private val mockApiService = mock<ApiService>()

    @Before
    fun setUp() {
        jokeRepository = JokesRepositoryImpl(
            mockApiService,
        )
    }

    @Test
    fun `Given Api response is successful, When api call, Returns Jokes List`() = runBlocking {
        // Given
        val mockJokeEntityList: List<JokeEntity> = mock()
        val mockResponse: Response<List<JokeEntity>> = mock {
            on { isSuccessful } doReturn true
            on { body() } doReturn mockJokeEntityList
        }

        whenever(mockApiService.getJokes(2)).thenReturn(mockResponse)

        // When
        val result = jokeRepository.getJokes(2)

        // Then
        assertNotNull(result)
    }

    @Test
    fun `Given Api response has failed, When api call, Returns error`() = runBlocking {
        // Given
        val mockResponse = mock<Response<List<JokeEntity>>> {
            on { isSuccessful } doReturn false
        }
        whenever(mockApiService.getJokes(2)).thenReturn(mockResponse)

        // When
        val result = jokeRepository.getJokes(2)

        // Then
        assertNotNull(result)
    }
}