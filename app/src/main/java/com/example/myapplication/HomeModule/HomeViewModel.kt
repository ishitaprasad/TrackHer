package com.example.myapplication.HomeModule

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeUiState(
    val cycleDay: String = "Day 12 of 28",
    val dailyInsight: String = "Your energy levels are rising. It's a great time for creative projects and high-intensity workouts."
)

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
}
