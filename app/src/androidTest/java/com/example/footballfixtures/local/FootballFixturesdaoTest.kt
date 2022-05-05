package com.example.footballfixtures.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.footballfixtures.data.local.FootballFixturesDao
import com.example.footballfixtures.data.local.FootballFixturesDatabase
import com.example.footballfixtures.utils.FakeDbData
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.junit.*
import javax.inject.Inject
import javax.inject.Named
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@HiltAndroidTest
@SmallTest
class FootballFixturesdaoTest {

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: FootballFixturesDatabase
    private lateinit var footballFixturesDao: FootballFixturesDao

    @Before
    fun setUp(){
        hiltRule.inject()
        footballFixturesDao = database.footballFixturesDao
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun should_Return_Empty_Competition_If_The_Table_Is_Empty() = runTest {
        val result = footballFixturesDao.getCompetitionsListFromDatabase()
        Assert.assertTrue(result.isEmpty() ?: false)
    }

    @Test
    fun onInsertingCompetitions_checkIf_RowCountIsCorrect() = runTest{
        val competitions = FakeDbData.getCompetitions(5)
        footballFixturesDao.saveCompetitions(competitions)
        val count  = footballFixturesDao?.getCompetitionsList()?.size
        assertEquals(5, count, "The row count should be 5")
    }
}