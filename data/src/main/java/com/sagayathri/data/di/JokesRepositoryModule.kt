package com.sagayathri.data.di

import com.sagayathri.data.repository.JokesRepository
import com.sagayathri.data.repository.impl.JokesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface JokesRepositoryModule {

    @Singleton
    @Binds
    fun bindJokesRepository(
        jokesRepositoryImpl: JokesRepositoryImpl
    ): JokesRepository
}