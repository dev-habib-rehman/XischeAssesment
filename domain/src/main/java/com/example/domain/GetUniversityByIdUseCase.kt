package com.example.domain

import com.example.common.model.University
import com.example.common.utils.Result
import com.example.data.repository.UniversityRepository
import javax.inject.Inject

class GetUniversityByIdUseCase @Inject constructor(private val repository: UniversityRepository) {
    suspend operator fun invoke(id: Int): Result<University> {
        return repository.getUniversityById(id)
    }
}