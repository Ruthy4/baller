package com.example.footballfixtures.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.footballfixtures.data.remote.dto.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FootballFixturesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCompetitions(competition: List<Competition>?)

    @Query("SELECT * FROM `competition`")
    fun getCompetitionsListFromDatabase(): Flow<List<Competition>>

    @Query("DELETE FROM competition" )
    fun deleteCompetition()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCompetitionTable(table: List<Table>?)

    @Query("SELECT * FROM `Table`")
    fun getTableListFromDatabase(): Flow<List<Table>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTeam(table: List<Team>?)

    @Query("SELECT * FROM `Team`")
    fun getTeamListFromDatabase(): Flow<List<Team>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFixtures(matches: List<Match>?)

    @Query("SELECT * FROM `Match`")
    fun getFixturesListFromDatabase(): Flow<List<Match>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTeamsSquad(matches: List<Squad>?)

    @Query("SELECT * FROM `Squad`")
    fun getTeamsSquadListFromDatabase(): Flow<List<Squad>>

}