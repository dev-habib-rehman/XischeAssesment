package com.example.data.network.retrofit

import com.example.common.model.University
import com.example.data.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.SEARCH_UNIVERSITIES)
    suspend fun getUniversities(
        @Query("country") country: String?,
    ): Response<List<University>>
}