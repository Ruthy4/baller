package com.example.footballfixtures.data

import com.example.footballfixtures.data.remote.dto.*
import com.example.footballfixtures.utils.TOKEN
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface FootballAPI {

    @GET("competitions?plan=TIER_ONE")
    @Headers("X-Auth-Token: $TOKEN")
    suspend fun getAllCompetitions() : CompetitionResponse

    @GET("competitions/{competitionId}/standings")
    @Headers("X-Auth-Token: $TOKEN")
    suspend fun getTableForCompetition(@Path("competitionId") competitionId: Int?): TableResponse

    @GET("competitions/{competitionId}/teams")
    @Headers("X-Auth-Token: $TOKEN")
    suspend fun getTeamForCompetition(@Path("competitionId") competitionId: Int?): TeamResponse

    @GET("competitions/{competitionId}/matches")
    @Headers("X-Auth-Token: $TOKEN")
    suspend fun getFixturesForCompetition(@Path("competitionId") competitionId: Int?): FixturesResponse

    @GET("teams/{teamId}")
    @Headers("X-Auth-Token: $TOKEN")
    suspend fun getSquadForTeam(@Path("teamId") teamId: Int?): SquadResponse

    @GET("matches?dateFrom=2022-05-05&dateTo=2022-05-15")
    @Headers("X-Auth-Token: $TOKEN")
    suspend fun getTodaysFixtures(): TodaysFixturesResponse
}
