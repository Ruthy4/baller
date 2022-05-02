package com.example.footballfixtures.presentation.ui.competitions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballfixtures.data.remote.dto.Competition
import com.example.footballfixtures.data.remote.dto.CompetitionResponse
import com.example.footballfixtures.domain.repository.FootballFixturesRepository
import com.example.footballfixtures.utils.Resource
import com.example.footballfixtures.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompetitionsViewModel @Inject constructor(private val repository: FootballFixturesRepository) :
    ViewModel() {

    private var _competitions = MutableLiveData<Resource<CompetitionResponse>>()
    val competitions: LiveData<Resource<CompetitionResponse>> get() = _competitions

//    fun getCompetitions() = viewModelScope.launch(Dispatchers.IO) {
//        try {
//            _competitions.postValue(Result.loading(null))
//            repository.getAllCompetitions().let {
//                if (it.isSuccessful) {
//                    _competitions.postValue(Result.success(it.body()))
//                } else{
//
//                _competitions.postValue(Result.error(it.errorBody().toString(), null))
//            }
//        }
//    } catch (e: Exception)
//    {
//        e.message
//    }
//}

    fun getCompetitions() {
        _competitions.value = Resource.Loading(null, "Loading....")
        viewModelScope.launch(Dispatchers.IO) {
            val getCompetitions = repository.getAllCompetitions()
            _competitions.postValue(getCompetitions)
        }
    }
}