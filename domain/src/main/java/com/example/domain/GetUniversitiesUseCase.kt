package com.example.domain

import com.example.common.model.University
import com.example.common.utils.Result
import com.example.data.repository.UniversityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUniversitiesUseCase @Inject constructor(private val repository: UniversityRepository) {
    suspend operator fun invoke(country: String, isRefresh: Boolean): Flow<Result<List<University>>> {
        return repository.getUniversities(country,isRefresh)
    }
}