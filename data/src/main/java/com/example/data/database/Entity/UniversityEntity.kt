package com.example.data.database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.common.model.University
import com.example.data.utils.Constants
import com.google.gson.annotations.SerializedName


@Entity(tableName = Constants.UNIVERSITY_ENTITY_NAME)
data class UniversityEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    @SerializedName("domains") val domains: List<String>? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("state-province") val stateProvince: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("alpha_two_code") val alphaTwoCode: String? = null,
//    @SerializedName("web_pages") val webPages: List<String>? = null
)

fun List<UniversityEntity>.asDomainModel(): List<University> {
    return this.map {
        University(
            id = it.id,
            name = it.name,
            country = it.country,
            stateProvince = it.stateProvince,
            alphaTwoCode = it.alphaTwoCode
        )
    }
}

fun UniversityEntity.asDomainModel(): University {
    return University(
        id = this.id,
        name = this.name,
        country = this.country,
        stateProvince = this.stateProvince,
        alphaTwoCode = this.alphaTwoCode
    )
}