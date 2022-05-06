package com.example.footballfixtures.data.mappers

import com.example.footballfixtures.data.remote.dto.Squad

class SquadDomainMapper(squad: List<Squad>?, teamId: Int?) {
    val squadDomain = squad?.map { squad ->

        Squad(
            id = squad.id,
            name = squad.name,
            position = squad.position,
            teamId = teamId
        )
    }
}