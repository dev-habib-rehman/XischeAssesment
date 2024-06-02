package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.Entity.UniversityEntity
import com.example.data.utils.Constants

@Dao
interface UniversitiesDao {
    @Query("SELECT * FROM ${Constants.UNIVERSITY_ENTITY_NAME}")
    suspend fun getAllUniversities(): List<UniversityEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(exchangeRates: List<UniversityEntity>)

    @Query("DELETE FROM ${Constants.UNIVERSITY_ENTITY_NAME}")
    suspend fun clearAll()

    @Query("SELECT * FROM ${Constants.UNIVERSITY_ENTITY_NAME} WHERE id = :universityId")
    suspend fun getUniversityById(universityId: Int): UniversityEntity?
}