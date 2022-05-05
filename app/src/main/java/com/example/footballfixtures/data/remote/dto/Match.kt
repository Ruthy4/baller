package com.example.footballfixtures.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.footballfixtures.utils.DateTimeUtils
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
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
)

data class FixturesResponse(@SerializedName("matches") val matches: List<Match>)

