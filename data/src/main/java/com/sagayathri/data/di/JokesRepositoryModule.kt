package com.sagayathri.data.di

import com.sagayathri.network.repository.JokesRepository
import com.sagayathri.network.repository.impl.JokesRepositoryImpl
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