package com.example.footballfixtures.data

import com.example.footballfixtures.data.remote.dto.Competition
import com.example.footballfixtures.data.remote.dto.CompetitionResponse
import com.example.footballfixtures.data.remote.dto.Match
import com.example.footballfixtures.utils.TOKEN
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FootballAPI {

    @GET("competitions?plan=TIER_ONE")
    @Headers("X-Auth-Token: $TOKEN")
    suspend fun getAllCompetitions() : CompetitionResponse

    @GET("competition")
    suspend fun getSingleCompetition() : Competition

    @GET("matches?dateFrom=2022-05-01&dateTo=2022-05-15")
    suspend fun getTodaysFixtures():List<Match>
}
