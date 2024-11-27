package com.sagayathri.data.datasource

import com.sagayathri.network.api.ApiService
import com.sagayathri.network.model.JokeEntity
import com.sagayathri.network.model.JokesEntity
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

internal class DataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : DataSource {

//    override suspend fun getJokes(limit: Int): Response<List<JokeEntity>> {
//        return apiService.getJokes(
//            limit = limit
//        )
//    }
}