package com.example.footballfixtures.domain.repository

import com.example.footballfixtures.data.remote.dto.Competition
import com.example.footballfixtures.utils.Resource

interface FootballFixturesRepository {
    suspend fun getAllCompetitions(areas: String, plan: String): Resource<List<Competition>>
}