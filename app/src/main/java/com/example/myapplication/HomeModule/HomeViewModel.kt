package com.example.myapplication.HomeModule

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class CycleHistoryItem(
    val dateRange: String,
    val lengthDays: Int
)

data class HomeUiState(
    val cycleDay: String = "Day 12 of 28",
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
}
