package com.example.myapplication.HomeModule

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.flow.update

import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.DataRepository
import kotlinx.coroutines.launch

data class CycleHistoryItem(
    val dateRange: String,
    val lengthDays: Int
)

data class HomeUiState(
    val cycleDay: String = "Day 12 of 28",
    val nextPeriodStartDate: String = "Feb 20, 2026",
    val dailyInsight: String = "Your energy levels are rising. It's a great time for creative projects and high-intensity workouts.",
    val history: List<CycleHistoryItem> = listOf(
        CycleHistoryItem("Jan 12 - Feb 9", 28),
        CycleHistoryItem("Dec 15 - Jan 11", 27),
        CycleHistoryItem("Nov 16 - Dec 14", 29)
    )
)


class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            DataRepository.nextPeriodStartDate.collect { date ->
                _uiState.update { it.copy(nextPeriodStartDate = date) }
            }
        }
    }

    fun updateNextPeriodDate(date: String) {
        DataRepository.updateNextPeriodStartDate(date)
    }
}
