package com.example.newsamplecalculaterapp.model.db


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsamplecalculaterapp.model.dataclass.CalculatorData
import com.example.newsamplecalculaterapp.model.dataclass.CalculatorTable
import kotlinx.coroutines.flow.Flow

@Dao
interface CalculatorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(notes: CalculatorData)

    @Query("SELECT * FROM ${CalculatorTable.TableName} ORDER BY id ASC")
    fun readAll(): Flow<List<CalculatorData>>

}