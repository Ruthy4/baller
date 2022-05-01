package com.example.footballfixtures.data.repository

import com.example.footballfixtures.data.FootballAPI
import com.example.footballfixtures.data.local.FootballFixturesDao
import com.example.footballfixtures.data.remote.dto.Competition
import com.example.footballfixtures.domain.repository.FootballFixturesRepository
import com.example.footballfixtures.utils.Resource
import com.example.footballfixtures.utils.apiCall
import javax.inject.Inject

class FootballFixturesRepositoryImpl @Inject constructor(val dao: FootballFixturesDao, val api: FootballAPI): FootballFixturesRepository {
    override suspend fun getAllCompetitions(areas: String, plan: String): Resource<List<Competition>> {
        return apiCall { api.getAllCompetitions(areas, plan) }
    }
}