package com.example.moduleb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.model.University
import com.example.common.utils.Result
import com.example.domain.GetUniversityByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UniversityDetailsVM @Inject constructor(
    private val getUniversitiesUseCase: GetUniversityByIdUseCase
) : ViewModel() {

    private val _universityData = MutableLiveData<University>()
    val universityData: LiveData<University> get() = _universityData

    private val _showMsg = MutableLiveData<String>()
    val showMsg: LiveData<String> get() = _showMsg

    fun getUniversities(id: Int) {
        viewModelScope.launch {
            when (val result = getUniversitiesUseCase.invoke(id)) {
                is Result.Success -> {
                    result.data?.let {
                        _universityData.value = it
                    } ?: kotlin.run { _showMsg.value = "No data found" }
                }

                is Result.Failure -> _showMsg.value = result.message
                else -> Unit
            }
        }
    }
}