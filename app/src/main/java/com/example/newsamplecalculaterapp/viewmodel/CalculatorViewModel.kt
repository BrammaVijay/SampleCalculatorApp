package com.example.newsamplecalculaterapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsamplecalculaterapp.model.dataclass.CalculatorData
import com.example.newsamplecalculaterapp.model.repository.CalculatorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(val repository: CalculatorRepository) : ViewModel() {

    init {

        viewModelScope.launch {
//            repository.readAllData()
        }
    }

    fun insertData(calculatorData: CalculatorData) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertData(calculatorData)
    }


}