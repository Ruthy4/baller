package com.example.footballfixtures

import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals

class FootballFixturesDaoTest {
    @Test
    fun should_Return_Empty_Competition_If_The_Table_Is_Empty() {
        val result = dao?.getCompetitionsList()
        Assert.assertTrue(result?.isEmpty() ?: false)
    }

    @Test
    fun onInsertingCompetitions_checkIf_RowCountIsCorrect(){
        val competitions = FakeDbData.getCompetitions(5)
        dao?.insert(competitions)
        val count  = dao?.getCompetitionsList()?.size
        assertEquals(5, count, "The row count should be 5")
    }
}