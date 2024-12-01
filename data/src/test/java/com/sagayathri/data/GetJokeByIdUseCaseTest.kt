package com.sagayathri.data

import com.sagayathri.data.async.DomainResult
import com.sagayathri.data.mapper.JokeListMapper
import com.sagayathri.data.mapper.JokeMapper
import com.sagayathri.data.model.Joke
import com.sagayathri.data.usecase.GetJokeByIdUseCase
import com.sagayathri.data.usecase.GetJokeListUseCase
import com.sagayathri.network.model.JokeEntity
import com.sagayathri.network.repository.JokesRepository
import com.sagayathri.network.utils.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GetJokeByIdUseCaseTest {
    private val mockRepository: JokesRepository = mock()
    private val mockMapper: JokeMapper = mock()

    private lateinit var useCase: GetJokeByIdUseCase

    @Before
    fun setUp() {
        useCase = GetJokeByIdUseCase(
            repository = mockRepository,
            dataMapper = mockMapper,
        )
    }

    @Test
    fun `Given jokeId is not valid, When use case is invoked, Then return error`() = runBlocking {
        // Given
        val error = mock<Throwable>()
        whenever(mockRepository.getJokeByID(2))
            .thenReturn(ApiResponse.Error(error))

        // When
        val result = useCase.invoke(2)

        // Then
        assert(result is DomainResult.Failure)
    }

    @Test
    fun `Given api call is success, When use case is invoked, Then map and return null`() = runBlocking {
        // Given
        val mockJokeEntityList: JokeEntity = mock()
        whenever(mockRepository.getJokeByID(2))
            .thenReturn(ApiResponse.Success(mockJokeEntityList))

        val jokeDomain: Joke = mock()
        whenever(mockMapper.map(mockJokeEntityList)).thenReturn(jokeDomain)

        // When
        val result = useCase.invoke(2)

        // Then
        assertNotNull(result)
        assert(result is DomainResult.Success)
        assertEquals(jokeDomain, (result as DomainResult.Success).data)
    }
}