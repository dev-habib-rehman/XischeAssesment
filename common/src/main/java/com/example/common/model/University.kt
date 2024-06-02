package com.example.common.model

import com.google.gson.annotations.SerializedName

data class University(
    val id: Int? = null,
    @SerializedName("domains") val domains: List<String>? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("state-province") val stateProvince: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("alpha_two_code") val alphaTwoCode: String? = null,
    @SerializedName("web_pages") val webPages: List<String>? = null
)
