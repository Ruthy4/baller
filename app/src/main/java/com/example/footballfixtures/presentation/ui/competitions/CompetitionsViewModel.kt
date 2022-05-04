package com.example.footballfixtures.presentation.ui.competitions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballfixtures.data.remote.dto.Competition
import com.example.footballfixtures.data.remote.dto.CompetitionResponse
import com.example.footballfixtures.domain.repository.FootballFixturesRepository
import com.example.footballfixtures.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompetitionsViewModel @Inject constructor(private val repository: FootballFixturesRepository) :
    ViewModel() {

    private var _competitions = MutableLiveData<Resource<CompetitionResponse>>()
    val competitions: LiveData<Resource<CompetitionResponse>> get() = _competitions

    fun getCompetitions() {
        _competitions.value = Resource.Loading(null, "Loading....")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val getCompetitions = repository.getAllCompetitions()
                _competitions.postValue(getCompetitions)
            } catch (e: Exception) {
                e.message
            }
        }
    }

    fun saveCompetitionToDatabase(competition: List<Competition>?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.saveCompetitions(competition)
            } catch (e: Exception) {
                e.message
            }
        }

    }
}