package com.example.footballfixtures.data.repository

import androidx.lifecycle.LiveData
import com.example.footballfixtures.data.FootballAPI
import com.example.footballfixtures.data.local.FootballFixturesDao
import com.example.footballfixtures.data.remote.dto.*
import com.example.footballfixtures.domain.repository.FootballFixturesRepository
import com.example.footballfixtures.utils.Resource
import com.example.footballfixtures.utils.apiCall
import javax.inject.Inject

class FootballFixturesRepositoryImpl @Inject constructor(val dao: FootballFixturesDao, private val api: FootballAPI): FootballFixturesRepository {
    override suspend fun getAllCompetitions(): Resource<CompetitionResponse> {
        return apiCall { api.getAllCompetitions() }
    }

    override suspend fun getTableForCompetition(competitionId: Int?): Resource<TableResponse> {
        return apiCall { api.getTableForCompetition(competitionId) }
    }

    override suspend fun getTeamForCompetition(competitionId: Int?): Resource<TeamResponse> {
        return apiCall { api.getTeamForCompetition(competitionId) }
    }

    override suspend fun getFixturesForCompetition(competitionId: Int?): Resource<FixturesResponse> {
        return apiCall { api.getFixturesForCompetition(competitionId) }
    }

    override suspend fun getSquadForTeam(teamId: Int?): Resource<SquadResponse> {
        return apiCall { api.getSquadForTeam(teamId) }
    }

    override suspend fun saveCompetitions(competition: List<Competition>?) {
        dao.saveCompetitions(competition)
    }

}