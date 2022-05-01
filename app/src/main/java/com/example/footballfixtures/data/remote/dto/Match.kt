package com.example.footballfixtures.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.footballfixtures.utils.DateTimeUtils
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class Match(
    @SerializedName("id") @PrimaryKey(autoGenerate = false) val id: Int,
    @SerializedName("competition") val competition: Competition?,
    @SerializedName("competitionId") var competitionId: Int?,
    @SerializedName("utcDate") val utcDate: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("stage") val stage: String?,
    @SerializedName("score") val score: Score?,
    @SerializedName("homeTeam") val homeTeam: Team?,
    @SerializedName("awayTeam") val awayTeam: Team?,
    @SerializedName("referees") val referees: List<Referee>?,
    val date: Date? = DateTimeUtils.toDate(utcDate)
) {
}
