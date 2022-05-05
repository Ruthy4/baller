package com.example.footballfixtures.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

@Entity(tableName = "team")
data class Team(
    @SerializedName("competitionId") var competitionId: Int?,
    @SerializedName("id") @PrimaryKey(autoGenerate = false) val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("crestUrl") val crestUrl: String?,
    @SerializedName("shortName") val shortName: String?,
    @SerializedName("tla") val tla: String?
)

data class TeamResponse(
    @SerializedName("competition") val competition: Competition?,
    @SerializedName("teams") val teams: List<Team>?
)

