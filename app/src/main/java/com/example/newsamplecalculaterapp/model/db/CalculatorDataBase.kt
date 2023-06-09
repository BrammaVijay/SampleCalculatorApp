package com.example.newsamplecalculaterapp.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsamplecalculaterapp.model.dataclass.CalculatorData

@Database(entities = [CalculatorData::class], version = 1, exportSchema = false)

abstract class CalculatorDataBase :RoomDatabase(){

    abstract fun calculatorDao():CalculatorDao
    /*companion object{
        @Volatile
        var INSTANCE:CalculatorDataBase?=null

        @Synchronized
        fun getDatabase(context: Context):CalculatorDataBase{
            val tempInstance= INSTANCE
            if (tempInstance!=null)
            {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,CalculatorDataBase::class.java,
                    "Calculation_New"
                ).build()

                INSTANCE=instance
                return instance
            }
        }
    }*/
}

object CalculatorDataBases{
    const val CalculatorDatabaseName = "calculator.db"

}