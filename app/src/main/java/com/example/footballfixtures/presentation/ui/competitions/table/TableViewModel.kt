package com.example.footballfixtures.presentation.ui.competitions.table

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballfixtures.data.remote.dto.TableResponse
import com.example.footballfixtures.domain.repository.FootballFixturesRepository
import com.example.footballfixtures.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableViewModel @Inject constructor(private val repository: FootballFixturesRepository): ViewModel() {

    private var _competitionsTable = MutableLiveData<Resource<TableResponse>>()
    val competitionsTable: LiveData<Resource<TableResponse>> get() = _competitionsTable

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
}