package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.Entity.UniversityEntity
import com.example.data.database.dao.UniversitiesDao

@Database(
    entities = [UniversityEntity::class], version = 1, exportSchema = false
)
abstract class UniversitiesDatabase : RoomDatabase() {
    abstract fun universitiesDao(): UniversitiesDao
}