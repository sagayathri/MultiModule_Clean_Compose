package com.sagayathri.data.di

import com.sagayathri.data.datasource.DataSource
import com.sagayathri.data.datasource.DataSourceImpl
import com.sagayathri.network.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiService): DataSource =
        DataSourceImpl(apiService)
}