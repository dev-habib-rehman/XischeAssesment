package com.example.data.repository

import com.example.common.model.University
import com.example.common.utils.Result
import kotlinx.coroutines.flow.Flow


interface UniversityRepository {
    suspend fun getUniversities(country: String, isRefresh: Boolean): Flow<Result<List<University>>>
    suspend fun getUniversityById(id: Int): Result<University>
}