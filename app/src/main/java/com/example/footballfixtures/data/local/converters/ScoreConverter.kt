package com.example.footballfixtures.data.local.converters

import androidx.room.TypeConverter
import com.example.footballfixtures.data.remote.dto.Score
import com.google.gson.Gson
import java.util.*

class ScoreConverter {
    companion object {

        @TypeConverter
        @JvmStatic
        fun fromStringToScore(value: String?): Score? {
            return if (value == null) null else Gson().fromJson(value, Score::class.java)
        }

        @TypeConverter
        @JvmStatic
        fun fromScoreToString(score: Score?): String? {
            return Gson().toJson(score)
        }
    }
}