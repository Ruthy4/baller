package com.example.footballfixtures.presentation.ui.competitions.team.squad

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballfixtures.data.remote.dto.Competition
import com.example.footballfixtures.data.remote.dto.SquadResponse
import com.example.footballfixtures.domain.repository.FootballFixturesRepository
import com.example.footballfixtures.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SquadViewModel @Inject constructor(private val repository: FootballFixturesRepository): ViewModel() {

    private var _Squad = MutableLiveData<Resource<SquadResponse>>()
    val squad: LiveData<Resource<SquadResponse>> get() = _Squad

    fun getTeamForSquad(teamId : Int?) {
        _Squad.postValue(Resource.Loading(null, "Loading...."))
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _Squad.postValue(repository.getSquadForTeam(teamId))
            } catch (e: Exception) {
                e.message
            }
        }
    }

}