package com.example.footballfixtures.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.footballfixtures.data.local.converters.*
import com.example.footballfixtures.data.remote.dto.*

@Database(
    entities = [Competition::class, Table::class, Team::class, Match::class, Squad::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    TeamConverter::class,
    FixturesConverter::class,
    CompetitionConverter::class,
    ScoreConverter::class,
    RefereeConverter::class,
    DateConverter::class
)
abstract class FootballFixturesDatabase : RoomDatabase() {
    abstract val footballFixturesDao: FootballFixturesDao
}