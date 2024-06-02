package com.example.modulea.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.model.University
import com.example.common.utils.Result
import com.example.common.utils.SingleLiveEvent
import com.example.domain.GetUniversitiesUseCase
import com.example.modulea.utils.adpaterUtils.GenericRecyclerItem
import com.example.modulea.utils.adpaterUtils.toRecyclerItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversitiesListVM @Inject constructor(
    private val getUniversitiesUseCase: GetUniversitiesUseCase
) : ViewModel() {

    private val _universitiesData = MutableLiveData<List<GenericRecyclerItem>>()
    val universitiesData: LiveData<List<GenericRecyclerItem>> get() = _universitiesData

    private val _showDismissLoading = MutableLiveData(true)
    val showDismissLoading: LiveData<Boolean> get() = _showDismissLoading

    private val _showMsg = MutableLiveData<String>()
    val showMsg: LiveData<String> get() = _showMsg

    private val _onUniversityItemClick = SingleLiveEvent<University>()
    val onUniversityItemClick: LiveData<University> get() = _onUniversityItemClick

    val onUniversityItemClickHandler: (Any) -> Unit = { item ->
        if (item is University) _onUniversityItemClick.value = item
    }

    fun getUniversities(country: String = "Pakistan", isRefresh: Boolean = false) {
        viewModelScope.launch {
            getUniversitiesUseCase.invoke(country, isRefresh).collect { result ->
                when (result) {
                    is Result.Empty -> Unit
                    is Result.Failure -> {
                        _showDismissLoading.value = false
                        _showMsg.value = result.message
                    }

                    is Result.Loading -> _showDismissLoading.value = true
                    is Result.Success -> {
                        _showDismissLoading.value = false
                        result.data?.let { data ->
                            _universitiesData.value = data.map { it.toRecyclerItem() }
                        } ?: kotlin.run {
                            _showMsg.value = "No Data Found"
                        }
                    }
                }
            }
        }
    }
}