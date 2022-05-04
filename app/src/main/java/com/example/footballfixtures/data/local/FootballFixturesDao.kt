package com.example.footballfixtures.data.local

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.footballfixtures.data.remote.dto.Competition
import kotlinx.coroutines.flow.Flow

@Dao
interface FootballFixturesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCompetitions(competition: List<Competition>?)

    @Query("SELECT * FROM `competition`")
    @VisibleForTesting
    fun getCompetitionsList(): LiveData<List<Competition>>

}