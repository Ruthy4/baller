package com.example.footballfixtures.data.remote.dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import kotlinx.parcelize.Parcelize
import java.lang.reflect.Type


@Entity
@Parcelize
@TypeConverters(CompetitionTypeConverters::class)
data class Competition(
    @SerializedName("id") @PrimaryKey(autoGenerate = false) val id: Int,
    @SerializedName("name") val name: String?
): Parcelable

data class CompetitionResponse(@SerializedName("count") val count:Int?,
                               @SerializedName("competitions") val competitions: List<Competition>?)

object CompetitionTypeConverters {
    @TypeConverter
    fun stringToMeasurements(json: String?): List<Competition> {
        val gson = Gson()
        val type: Type =
            object : TypeToken<List<Competition?>?>() {}.type
        return gson.fromJson<List<Competition>>(json, type)
    }

    @TypeConverter
    fun measurementsToString(list: List<Competition?>?): String {
        val gson = Gson()
        val type: Type =
            object : TypeToken<List<Competition?>?>() {}.type
        return gson.toJson(list, type)
    }
}