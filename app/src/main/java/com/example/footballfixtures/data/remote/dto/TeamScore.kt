package com.example.footballfixtures.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TeamScore(
    @SerializedName("homeTeam") val homeTeamScore: Int?,
    @SerializedName("awayTeam") val awayTeamScore: Int?
)