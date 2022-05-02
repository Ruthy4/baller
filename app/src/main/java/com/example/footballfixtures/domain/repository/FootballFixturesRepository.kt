package com.example.footballfixtures.domain.repository

import com.example.footballfixtures.data.remote.dto.CompetitionResponse
import com.example.footballfixtures.utils.Resource

interface FootballFixturesRepository {
    suspend fun getAllCompetitions(): Resource<CompetitionResponse>
}