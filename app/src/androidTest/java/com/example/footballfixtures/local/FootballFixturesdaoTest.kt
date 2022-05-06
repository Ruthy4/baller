package com.example.footballfixtures.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.footballfixtures.data.local.FootballFixturesDao
import com.example.footballfixtures.data.local.FootballFixturesDatabase
import com.example.footballfixtures.data.remote.dto.Competition
import com.example.footballfixtures.utils.FakeDbData
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class FootballFixturesdaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: FootballFixturesDatabase
    private lateinit var footballFixturesDao: FootballFixturesDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FootballFixturesDatabase::class.java
        )
            .allowMainThreadQueries().build()
        footballFixturesDao = database.footballFixturesDao
    }

    //competitions dao
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun should_Return_Empty_Table_If_The_Competition_Table_Is_Empty() = runTest {

        val result = footballFixturesDao.getCompetitionsListFromDatabase().take(1).toList()
        assertThat(result.isEmpty())
    }

    @Test
    fun testSaveCompetition() = runTest {

        val competition = listOf(
            Competition(
                id = 1,
                name = ""
            )
        )
        footballFixturesDao.saveCompetitions(competition)

        val getSavedCompetitions =
            footballFixturesDao.getCompetitionsListFromDatabase().take(1).toList()

        assertThat(getSavedCompetitions).isNotEmpty()
        assertThat(getSavedCompetitions).contains(competition)
    }


    //table dao
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun should_Return_Empty_Table_If_The_Table_Is_Empty() = runTest {

        val result = footballFixturesDao.getTableListFromDatabase(1).take(1).toList()
        assertThat(result.isEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun onInsertingTable_checkIf_RowCountIsCorrect() = runTest {
        val table = FakeDbData.getFakeTable(5, 1)

        footballFixturesDao.saveCompetitionTable(table)

        val count = footballFixturesDao.getTableListFromDatabase(1).take(1).toList().size
        assertEquals(1, count)
    }


    // Fixtures Dao
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun should_Return_Empty_Table_If_The_Fixtures_Table_Is_Empty() = runTest {

        val result = footballFixturesDao.getFixturesListFromDatabase(1).take(1).toList()
        assertThat(result.isEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun onInsertingFixtures_checkIf_RowCountIsCorrect() = runTest {
        val table = FakeDbData.getFakeMatches(5)

        footballFixturesDao.saveFixtures(table)

        val count = footballFixturesDao.getFixturesListFromDatabase(1).take(1).toList().size
        assertEquals(1, count)
    }


    // Team Dao
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun should_Return_Empty_Table_If_The_Team_Table_Is_Empty() = runTest {

        val result = footballFixturesDao.getTeamListFromDatabase(1).take(1).toList()
        assertThat(result.isEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun onInsertingTeams_checkIf_RowCountIsCorrect() = runTest {
        val table = FakeDbData.getFakeTeams(5)

        footballFixturesDao.saveTeam(table)

        val count = footballFixturesDao.getTeamListFromDatabase(1).take(1).toList().size
        assertEquals(1, count)
    }


    //squad
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun should_Return_Empty_Table_If_The_Squad_Table_Is_Empty() = runTest {

        val result = footballFixturesDao.getTeamsSquadListFromDatabase(1).take(1).toList()
        assertThat(result.isEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun onInsertingTeamsSquad_checkIf_RowCountIsCorrect() = runTest {
        val table = FakeDbData.getFakeSquad(5)

        footballFixturesDao.saveTeamsSquad(table)

        val count = footballFixturesDao.getTeamListFromDatabase(1).take(1).toList().size
        assertEquals(1, count)
    }
}