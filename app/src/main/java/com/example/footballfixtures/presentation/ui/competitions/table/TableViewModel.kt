package com.example.footballfixtures.presentation.ui.competitions.table

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballfixtures.data.remote.dto.Competition
import com.example.footballfixtures.data.remote.dto.Table
import com.example.footballfixtures.data.remote.dto.TableResponse
import com.example.footballfixtures.domain.repository.FootballFixturesRepository
import com.example.footballfixtures.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableViewModel @Inject constructor(private val repository: FootballFixturesRepository): ViewModel() {

    private var _competitionsTable = MutableLiveData<Resource<TableResponse>>()
    val competitionsTable: LiveData<Resource<TableResponse>> get() = _competitionsTable

    private var _savedTable = MutableLiveData<Resource<List<Table>>>()
    val savedTable: LiveData<Resource<List<Table>>> get() = _savedTable

    fun getCompetitionTables(competitionId: Int?){
        _competitionsTable.postValue(Resource.Loading(null, "Loading..."))
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _competitionsTable.postValue(repository.getTableForCompetition(competitionId))
            } catch (e: Exception) {
                e.message
            }
        }
    }

    fun saveCompetitionTable(table: List<Table>?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.saveCompetitionTable(table)
            } catch (e: Exception) {
                e.message
            }
        }
    }

    fun getTableListFromDatabase(competitionId: Int?) {
        _savedTable.value = Resource.Loading(null, "Loading....")
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTableListFromDatabase (competitionId).collect {
                _savedTable.postValue(it)
            }
        }
    }
}