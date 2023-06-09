package com.example.newsamplecalculaterapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.newsamplecalculaterapp.model.db.CalculatorDao
import com.example.newsamplecalculaterapp.model.db.CalculatorDataBase
import com.example.newsamplecalculaterapp.model.db.CalculatorDataBases
import com.example.newsamplecalculaterapp.session.SessionPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getDataBase(context: Application): CalculatorDataBase {
        return Room.databaseBuilder(
            context.applicationContext, CalculatorDataBase::class.java,
            CalculatorDataBases.CalculatorDatabaseName
        ).build()
    }

    @Singleton
    @Provides
    fun calculatorDataBase(appDataBase: CalculatorDataBase): CalculatorDao {
        return appDataBase.calculatorDao()
    }

    @Singleton
    @Provides
    fun provideSessionPref(@ApplicationContext context: Context) = SessionPref(context)
}