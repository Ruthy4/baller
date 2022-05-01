package com.example.footballfixtures.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.footballfixtures.data.remote.dto.Competition

@Database(entities = [Competition::class], version = 1, exportSchema = false)
abstract class FootballFixturesDatabase: RoomDatabase() {
}