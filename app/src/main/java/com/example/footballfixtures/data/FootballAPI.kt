package com.example.footballfixtures.data

import com.example.footballfixtures.data.remote.dto.Competition
import com.example.footballfixtures.data.remote.dto.Match
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballAPI {

    @GET("competitions")
    suspend fun getAllCompetitions(
        @Query("areas") areas: String,
        @Query("plan") plan: String
    ) : List<Competition>

    @GET("competition")
    suspend fun getSingleCompetition() : Competition

    @GET("matches?dateFrom=2022-05-01&dateTo=2022-05-15")
    suspend fun getTodaysFixtures():List<Match>
}
