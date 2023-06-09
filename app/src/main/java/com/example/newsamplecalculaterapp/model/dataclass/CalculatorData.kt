package com.example.newsamplecalculaterapp.model.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CalculatorTable.TableName)
data class CalculatorData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
   // val expression: String,
    val result: String,
    //val timestamp: Long = System.currentTimeMillis()
)

object CalculatorTable{
    const val TableName = "CalculatorTable"
}
