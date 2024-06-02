package com.example.data.repository

import com.example.common.model.University
import com.example.common.utils.Result
import com.example.data.database.Entity.asDomainModel
import com.example.data.database.dao.UniversitiesDao
import com.example.data.network.apiHelper.ApiHelper
import com.example.data.network.model.toDbModel
import com.example.data.network.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val universityLocalDataSource: UniversitiesDao,
    private val universityRemoteDataSource: ApiService,
) : UniversityRepository, ApiHelper() {
    override suspend fun getUniversities(
        country: String, isRefresh: Boolean
    ): Flow<Result<List<University>>> = flow {
        val universitiesData = universityLocalDataSource.getAllUniversities()

        if (!universitiesData.isNullOrEmpty() && !isRefresh) {
            emit(Result.Success(universitiesData.asDomainModel(), 200))
            return@flow
        }

        val result = serviceCall { universityRemoteDataSource.getUniversities(country) }

        result.collect { apiResult ->
            when (apiResult) {
                is Result.Success -> {
                    apiResult.data?.toDbModel()?.let { universities ->
                        universityLocalDataSource.clearAll()
                        universityLocalDataSource.insertAll(universities)
                        emit(
                            Result.Success(
                                universityLocalDataSource.getAllUniversities()?.asDomainModel(), 200
                            )
                        )
                    }
                }

                is Result.Failure -> {
                    emit(
                        Result.Failure(
                            message = apiResult.message,
                            errorCode = apiResult.errorCode,
                            exception = apiResult.exception
                        )
                    )
                }

                else -> Unit
            }
        }
    }

    override suspend fun getUniversityById(id: Int): Result<University> {
        val universitiesData = universityLocalDataSource.getUniversityById(id)
        return if (universitiesData != null) Result.Success(
            universitiesData.asDomainModel(), 200
        )
        else Result.Failure(message = "University not found", errorCode = 404)
    }
}