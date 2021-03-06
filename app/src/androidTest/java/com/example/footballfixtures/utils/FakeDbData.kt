package com.example.footballfixtures.utils

import com.example.footballfixtures.data.remote.dto.*
import java.util.*

object FakeDbData {
    fun getFakeMatches(size: Int): MutableList<Match> {
        val list = mutableListOf<Match>()
        for (i in 0 until size) {
            list.add(
                Match(
                    i,
                    Competition(i, "Competition$i"),
                    0,
                    DateTimeUtils.formatDate(Date()),
                    "Status $i",
                    "",
                    null,
                    null,
                    null,
                    null
                )
            )
        }

        return list
    }


    fun getCompetitions(size: Int): MutableList<Competition> {
        val list = mutableListOf<Competition>()
        for (i in 0 until size) {
            list.add(
                Competition(i, "Competition$i")
            )
        }

        return list
    }

    fun getFakeCompetition(): Competition {
        return Competition(1, "Competition$1")
    }

    fun getFakeSquad(size: Int): MutableList<Squad> {
        val list = mutableListOf<Squad>()
        for (i in 0 until size) {
            list.add(
                Squad(
                    i,
                    "Status $i",
                    "Position $i",
                    1
                )
            )
        }

        return list
    }

    fun getFakeTable(size: Int, competitionId:Int): MutableList<Table> {
        val list = mutableListOf<Table>()
        for (i in 1 .. size) {
            list.add(
                Table(
                    competitionId,
                    i,
                    i,
                    Team(competitionId, i, "", "", "", ""),
                    i, i, i, i, i, i, i, i
                )
            )
        }

        return list
    }

    fun getFakeTeams(size: Int): MutableList<Team> {
        val list = mutableListOf<Team>()
        for (i in 0 until size) {
            list.add(
                Team(1, i, "", "", "", "")
            )
        }

        return list
    }

    fun getFakeTeam(): Team{
        return Team(1, 1, "Chelsea", "", "", "")
    }
}