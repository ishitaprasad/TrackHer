package com.example.myapplication.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object DataRepository {
    private val _nextPeriodStartDate = MutableStateFlow("Feb 20, 2026")
    val nextPeriodStartDate: StateFlow<String> = _nextPeriodStartDate.asStateFlow()

    fun updateNextPeriodStartDate(date: String) {
        _nextPeriodStartDate.value = date
    }
}
