package com.example.footballfixtures.domain.repository

import com.example.footballfixtures.data.remote.dto.*
import com.example.footballfixtures.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FootballFixturesRepository {
    suspend fun getAllCompetitions(): Resource<CompetitionResponse>

    suspend fun getTableForCompetition(competitionId: Int?): Resource<TableResponse>

    suspend fun getTeamForCompetition(competitionId: Int?): Resource<TeamResponse>

    suspend fun getFixturesForCompetition(competitionId: Int?): Resource<FixturesResponse>

    suspend fun getSquadForTeam(teamId: Int?): Resource<SquadResponse>

    suspend fun saveCompetitions(competition: List<Competition>?)

    suspend fun getCompetitionsListFromDatabase(): Flow<Resource<List<Competition>>>

    suspend fun deleteAllCompetitions()

    suspend fun saveCompetitionTable(table: List<Table>?)

    suspend fun getTableListFromDatabase(): Flow<Resource<List<Table>>>

    suspend fun saveTeam(team: List<Team>?)

    suspend fun getTeamListFromDatabase(): Flow<Resource<List<Team>>>

    suspend fun saveFixtures(matches: List<Match>?)

    suspend fun getFixturesListFromDatabase() : Flow<Resource<List<Match>>>

    suspend fun saveTeamsSquad(squad: List<Squad>?)

    suspend fun getTeamsSquadFromDatabase() : Flow<Resource<List<Squad>>>
}