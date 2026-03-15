package com.example.myapplication.OnBoardingModule.featDetails.Screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.OnBoardingModule.featDetails.data.PredictCycleRequest
import com.example.myapplication.OnBoardingModule.featDetails.data.PredictPcosRequest
import com.example.myapplication.api.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class QuestionType {
    MULTIPLE_CHOICE,
    DATE_PICKER,
    TEXT_INPUT
}

data class QuestionStep(
    val stepIndex: Int,
    val questionText: String,
    val supportingText: String?,
    val options: List<String>? = null, // For multiple choice questions
    val questionType: QuestionType = QuestionType.MULTIPLE_CHOICE,
    val inputLabel: String? = null // For text input questions
)

data class DetailsState(
    val currentStepIndex: Int = 0,
    val selectedOptionIndex: Int? = null,
    val answers: Map<Int, Int> = emptyMap(), // currentStepIndex -> selectedOptionIndex
    val lastPeriodStartDate: String? = null, // Format: "2026-03-01"
    val age: String = "",
    val heightCm: String = "",
    val weightKg: String = ""
)

class DetailsViewModel : ViewModel() {

    val questions = listOf(
        QuestionStep(
            stepIndex = 1,
            questionText = "When did your last period start?",
            supportingText = "Select the first day of your most recent period",
            questionType = QuestionType.DATE_PICKER
        ),
        QuestionStep(
            stepIndex = 2,
            questionText = "How long is your typical cycle?",
            supportingText = "A cycle is counted from the first day of one period to the first day of the next.",
            options = listOf("21–28 days", "29–35 days", "36-40 days", "I am not sure"),
            questionType = QuestionType.MULTIPLE_CHOICE
        ),
        QuestionStep(
            stepIndex = 3,
            questionText = "How long does your period usually last?",
            supportingText = "Average is 3-7 days",
            options = listOf("Less than 3 days", "3-5 days", "6-7 days", "More than 7 days"),
            questionType = QuestionType.MULTIPLE_CHOICE
        ),
        QuestionStep(
            stepIndex = 4,
            questionText = "How would you describe your flow?",
            supportingText = "This helps personalize symptom tracking and predictions.",
            options = listOf("Light", "Moderate", "Heavy"),
            questionType = QuestionType.MULTIPLE_CHOICE
        ),
        QuestionStep(
            stepIndex = 5,
            questionText = "Are your periods regular?",
            supportingText = "Regular means they come at predictable intervals (21-35 days)",
            options = listOf("Yes, pretty regular", "No, they vary a lot", "I don't know"),
            questionType = QuestionType.MULTIPLE_CHOICE
        ),
        QuestionStep(
            stepIndex = 6,
            questionText = "What is your age?",
            supportingText = "Enter your current age",
            questionType = QuestionType.TEXT_INPUT
        ),
        QuestionStep(
            stepIndex = 7,
            questionText = "What is your height and weight?",
            supportingText = "We will calculate your BMI from your height and weight.",
            questionType = QuestionType.TEXT_INPUT,
            inputLabel = "Height (cm) and Weight (kg)"
        ),
        QuestionStep(
            stepIndex = 8,
            questionText = "Have you experienced sudden weight gain?",
            supportingText = "Unexplained increase in body weight.",
            options = listOf("Yes", "No"),
            questionType = QuestionType.MULTIPLE_CHOICE
        ),
        QuestionStep(
            stepIndex = 9,
            questionText = "Have you noticed excess hair growth?",
            supportingText = "Growth on face, chest, or back (hirsutism).",
            options = listOf("Yes", "No"),
            questionType = QuestionType.MULTIPLE_CHOICE
        ),
        QuestionStep(
            stepIndex = 10,
            questionText = "Have you noticed any skin darkening?",
            supportingText = "Typically around the neck or other skin folds.",
            options = listOf("Yes", "No"),
            questionType = QuestionType.MULTIPLE_CHOICE
        ),
        QuestionStep(
            stepIndex = 11,
            questionText = "Are you experiencing hair loss?",
            supportingText = "Thinning of hair on the scalp.",
            options = listOf("Yes", "No"),
            questionType = QuestionType.MULTIPLE_CHOICE
        ),
        QuestionStep(
            stepIndex = 12,
            questionText = "Do you have frequent pimples or acne?",
            supportingText = "Persistent skin breakouts.",
            options = listOf("Yes", "No"),
            questionType = QuestionType.MULTIPLE_CHOICE
        ),
        QuestionStep(
            stepIndex = 13,
            questionText = "Do you consume fast food regularly?",
            supportingText = "Eating processed or oily food frequently.",
            options = listOf("Yes", "No"),
            questionType = QuestionType.MULTIPLE_CHOICE
        ),
        QuestionStep(
            stepIndex = 14,
            questionText = "Do you exercise regularly?",
            supportingText = "Physical activity at least 3 times a week.",
            options = listOf("Yes", "No"),
            questionType = QuestionType.MULTIPLE_CHOICE
        )
    )

    private val _uiState = MutableStateFlow(DetailsState())
    val uiState: StateFlow<DetailsState> = _uiState.asStateFlow()

    fun selectOption(optionIndex: Int) {
        _uiState.update { it.copy(selectedOptionIndex = optionIndex) }
    }

    fun setLastPeriodStartDate(date: String) {
        _uiState.update { it.copy(lastPeriodStartDate = date) }
    }

    fun setAge(age: String) {
        _uiState.update { it.copy(age = age) }
    }

    fun setHeight(height: String) {
        _uiState.update { it.copy(heightCm = height) }
    }

    fun setWeight(weight: String) {
        _uiState.update { it.copy(weightKg = weight) }
    }

    fun nextStep() {
        _uiState.update { currentState ->
            val newAnswers = currentState.answers.toMutableMap()
            val currentQuestion = questions[currentState.currentStepIndex]
            
            when (currentQuestion.questionType) {
                QuestionType.MULTIPLE_CHOICE -> {
                    if (currentState.selectedOptionIndex != null) {
                        newAnswers[currentState.currentStepIndex] = currentState.selectedOptionIndex!!
                    }
                }
                else -> {}
            }
            
            if (currentState.currentStepIndex < questions.size - 1) {
                val nextStep = currentState.currentStepIndex + 1
                val nextSelectedOption = newAnswers[nextStep]
                
                currentState.copy(
                    currentStepIndex = nextStep,
                    selectedOptionIndex = nextSelectedOption,
                    answers = newAnswers
                )
            } else {
                currentState.copy(answers = newAnswers)
            }
        }
    }

    private fun calculateBmi(): Float? {
        val state = _uiState.value
        val heightMeters = state.heightCm.toFloatOrNull()?.div(100f) ?: return null
        val weightKg = state.weightKg.toFloatOrNull() ?: return null
        return if (heightMeters > 0) weightKg / (heightMeters * heightMeters) else null
    }

    private fun mapYesNoToBinary(answerIndex: Int?): Int {
        return if (answerIndex == 0) 1 else 0
    }

    fun buildPredictCycleRequest(userId: Int): PredictCycleRequest? {
        val state = _uiState.value
        
        val lastPeriodStartDate = state.lastPeriodStartDate ?: return null
        val cycleLengthRange = questions[1].options?.getOrNull(state.answers[1] ?: -1) ?: return null
        val periodDuration = questions[2].options?.getOrNull(state.answers[2] ?: -1) ?: return null
        val flow = questions[3].options?.getOrNull(state.answers[3] ?: -1) ?: return null
        val regularity = questions[4].options?.getOrNull(state.answers[4] ?: -1) ?: return null
        val age = state.age.toFloatOrNull() ?: return null
        val bmi = calculateBmi() ?: return null
        
        return PredictCycleRequest(
            userId = userId,
            lastPeriodStartDate = lastPeriodStartDate,
            cycleLengthRange = cycleLengthRange,
            periodDuration = periodDuration,
            flow = flow,
            regularity = regularity,
            age = age,
            bmi = bmi
        )
    }

    fun buildPredictPcosRequest(userId: Int): PredictPcosRequest? {
        val state = _uiState.value
        val age = state.age.toIntOrNull() ?: return null
        val bmi = calculateBmi() ?: return null

        return PredictPcosRequest(
            userId = userId,
            age = age,
            bmi = bmi,
            weightGain = mapYesNoToBinary(state.answers[7]),
            hairGrowth = mapYesNoToBinary(state.answers[8]),
            skinDarkening = mapYesNoToBinary(state.answers[9]),
            hairLoss = mapYesNoToBinary(state.answers[10]),
            pimples = mapYesNoToBinary(state.answers[11]),
            fastFood = mapYesNoToBinary(state.answers[12]),
            exercise = mapYesNoToBinary(state.answers[13])
        )
    }

    fun submitAllRequests(
        userId: Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val cycleRequest = buildPredictCycleRequest(userId)
                val pcosRequest = buildPredictPcosRequest(userId)
                
                if (cycleRequest == null || pcosRequest == null) {
                    onError("Please complete all questions first")
                    return@launch
                }
                
                val cycleResponse = RetrofitClient.apiService.predictCycle(cycleRequest)
                val pcosResponse = RetrofitClient.apiService.predictPcos(pcosRequest)
                
                if (cycleResponse.isSuccessful && pcosResponse.isSuccessful) {
                    onSuccess()
                } else {
                    onError("One or more requests failed. Cycle: ${cycleResponse.code()}, PCOS: ${pcosResponse.code()}")
                }
            } catch (e: Exception) {
                onError("Network error: ${e.message}")
            }
        }
    }
}
