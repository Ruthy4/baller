package com.example.footballfixtures.domain.repository

import androidx.lifecycle.LiveData
import com.example.footballfixtures.data.remote.dto.*
import com.example.footballfixtures.utils.Resource

interface FootballFixturesRepository {
    suspend fun getAllCompetitions(): Resource<CompetitionResponse>

    suspend fun getTableForCompetition(competitionId: Int?): Resource<TableResponse>

    suspend fun getTeamForCompetition(competitionId: Int?): Resource<TeamResponse>

    suspend fun getFixturesForCompetition(competitionId: Int?): Resource<FixturesResponse>

    suspend fun getSquadForTeam(teamId: Int?) : Resource<SquadResponse>

   suspend fun saveCompetitions(competition: List<Competition>?)

}