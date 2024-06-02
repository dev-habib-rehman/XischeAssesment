package com.example.data.network.model

import com.example.common.model.University
import com.example.data.database.Entity.UniversityEntity
import com.google.gson.annotations.SerializedName

fun List<University>.toDbModel(): List<UniversityEntity> {
    return this.map {
        UniversityEntity(
            name = it.name,
            stateProvince = it.stateProvince,
            country = it.country,
            alphaTwoCode = it.alphaTwoCode,
        )
    }
}