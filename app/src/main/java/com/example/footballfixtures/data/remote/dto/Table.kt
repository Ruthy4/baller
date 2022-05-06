package com.example.footballfixtures.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

@Entity(tableName = "table")
data class Table(
    @SerializedName("competitionId") var competitionId: Int?,
    @SerializedName("id") @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("position") val position: Int?,
    @SerializedName("team") val team: Team,
    @SerializedName("playedGames") val playedGames: Int?,
    @SerializedName("won") val won: Int?,
    @SerializedName("draw") val draw: Int?,
    @SerializedName("lost") val lost: Int?,
    @SerializedName("points") val points: Int?,
    @SerializedName("goalsFor") val goalsFor: Int?,
    @SerializedName("goalsAgainst") val goalsAgainst: Int?,
    @SerializedName("goalDifference") val goalDifference: Int?
)

data class TableResponse(
    @SerializedName("competition") val competition: Competition,
    @SerializedName("standings") val standings: List<Standings>?
)
