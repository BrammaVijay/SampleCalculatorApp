package com.example.newsamplecalculaterapp.session

import android.content.Context

class SessionPref(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("SharedPref", 0)

    fun setCalculateTheValue(getValue: String) {
        sharedPreferences.apply {
            edit().putString(SessionPrefConstants.CALCULATE_VALUE, getValue)
                .apply()
        }
    }

    fun getCalculatorValue() =
        sharedPreferences.getString(SessionPrefConstants.CALCULATE_VALUE, "0") ?: "0"

}

object SessionPrefConstants {
    const val CALCULATE_VALUE = "calculateValue"
}