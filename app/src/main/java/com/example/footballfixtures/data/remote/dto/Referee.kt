package com.example.footballfixtures.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Referee(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("nationality") val nationality: String?
)