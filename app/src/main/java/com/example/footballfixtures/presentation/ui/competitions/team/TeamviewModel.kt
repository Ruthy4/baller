package com.example.footballfixtures.presentation.ui.competitions.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballfixtures.data.remote.dto.CompetitionResponse
import com.example.footballfixtures.data.remote.dto.TeamResponse
import com.example.footballfixtures.domain.repository.FootballFixturesRepository
import com.example.footballfixtures.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(private val repository: FootballFixturesRepository): ViewModel() {
    private var _team = MutableLiveData<Resource<TeamResponse>>()
    val team: LiveData<Resource<TeamResponse>> get() = _team

    fun getCompetitions(competitionId: Int?) {
        _team.value = Resource.Loading(null, "Loading....")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val getTeam = repository.getTeamForCompetition(competitionId)
                _team.postValue(getTeam)
            } catch (e: Exception) {
                e.message
            }
        }
    }
}