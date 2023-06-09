package com.example.newsamplecalculaterapp.model.repository

import com.example.newsamplecalculaterapp.model.dataclass.CalculatorData
import com.example.newsamplecalculaterapp.model.db.CalculatorDataBase
import javax.inject.Inject

class CalculatorRepository @Inject constructor(val dataBase:CalculatorDataBase) {

    suspend fun insertData(notes:CalculatorData)=dataBase.calculatorDao().insertData(notes)

     fun readAllData()=dataBase.calculatorDao().readAll()

}