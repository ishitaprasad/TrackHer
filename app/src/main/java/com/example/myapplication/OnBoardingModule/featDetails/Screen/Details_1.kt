package com.example.myapplication.OnBoardingModule.featDetails.Screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.components.CommonTextField
import com.example.myapplication.components.PrimaryButton
import com.example.myapplication.ui.theme.color_1F2937
import com.example.myapplication.ui.theme.color_E5E7EB
import com.example.myapplication.ui.theme.color_FDF2F8
import com.example.myapplication.ui.theme.color_FF6B9D
import com.example.myapplication.ui.theme.color_FFFFFF
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen1(
    onNextClicked: () -> Unit = {},
    viewModel: DetailsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentQuestion = viewModel.questions[uiState.currentStepIndex]
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_FDF2F8).padding(top = 64.dp, start = 45.dp, end = 45.dp, bottom = 50.dp)
    ) {
        Column(modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth()) {
            Text(
                text = "Let's Get to Know You", fontSize = 24.sp,
                fontWeight = FontWeight.W700,
                color = color_1F2937,
                textAlign = TextAlign.Center, modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth()
            )

            Text(
                text = "Help us personalize your experience with a few quick questions", fontSize = 14.sp,
                fontWeight = FontWeight.W400, color = color_1F2937,
                textAlign = TextAlign.Center,
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(24.dp))
                .border(1.dp, color_E5E7EB, RoundedCornerShape(24.dp))
                .background(color_FFFFFF, RoundedCornerShape(24.dp))
                .padding(top = 30.dp, bottom = 25.dp, start = 24.dp, end = 24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Step ${uiState.currentStepIndex + 1} of ${viewModel.questions.size}", fontSize = 14.sp,
                    fontWeight = FontWeight.W600, color = color_1F2937
                )
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = currentQuestion.questionText, fontSize = 18.sp,
                    fontWeight = FontWeight.W700, textAlign = TextAlign.Start,
                    modifier = Modifier.padding(bottom = 13.dp)
                )

                if (currentQuestion.supportingText != null) {
                    Text(
                        text = currentQuestion.supportingText,
                        fontSize = 14.sp, fontWeight = FontWeight.W400, textAlign = TextAlign.Start,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                }

                when (currentQuestion.questionType) {
                    QuestionType.MULTIPLE_CHOICE -> {
                        currentQuestion.options?.forEachIndexed { index, optionText ->
                            DetailItem(
                                text = optionText,
                                checked = uiState.selectedOptionIndex == index,
                                onCheckedChange = { if (it) viewModel.selectOption(index) }
                            )
                        }
                    }
                    QuestionType.DATE_PICKER -> {
                        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        val displayFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                        
                        val selectedDateText = uiState.lastPeriodStartDate?.let { dateStr ->
                            try {
                                val date = dateFormat.parse(dateStr)
                                date?.let { displayFormat.format(it) } ?: dateStr
                            } catch (e: Exception) {
                                dateStr
                            }
                        } ?: "Select date"
                        
                        DetailItem(
                            text = selectedDateText,
                            checked = false,
                            onCheckedChange = { showDatePicker = true },
                            isDatePicker = true
                        )
                        
                        if (showDatePicker) {
                            DatePickerDialog(
                                onDismissRequest = { showDatePicker = false },
                                confirmButton = {
                                    TextButton(onClick = {
                                        datePickerState.selectedDateMillis?.let { millis ->
                                            val date = Date(millis)
                                            val formattedDate = dateFormat.format(date)
                                            viewModel.setLastPeriodStartDate(formattedDate)
                                        }
                                        showDatePicker = false
                                    }) {
                                        Text("OK")
                                    }
                                },
                                dismissButton = {
                                    TextButton(onClick = { showDatePicker = false }) {
                                        Text("Cancel")
                                    }
                                }
                            ) {
                                DatePicker(state = datePickerState)
                            }
                        }
                    }
                    QuestionType.TEXT_INPUT -> {
                        when (currentQuestion.stepIndex) {
                            6 -> {
                                CommonTextField(
                                    value = uiState.age,
                                    onValueChange = { viewModel.setAge(it) },
                                    label = "Age",
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            7 -> {
                                CommonTextField(
                                    value = uiState.heightCm,
                                    onValueChange = { viewModel.setHeight(it) },
                                    label = "Height (cm)",
                                    modifier = Modifier.fillMaxWidth()
                                )
                                CommonTextField(
                                    value = uiState.weightKg,
                                    onValueChange = { viewModel.setWeight(it) },
                                    label = "Weight (kg)",
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }
            }

        }

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = if (uiState.currentStepIndex == viewModel.questions.size - 1) "Finish" else "Continue",
            color = color_FF6B9D,
            enabled = true
        ) {
            if (uiState.currentStepIndex == viewModel.questions.size - 1) {
                // Try to submit but always move to next screen (Home)
                viewModel.submitAllRequests(
                    userId = 1,
                    onSuccess = {
                        onNextClicked()
                    },
                    onError = { errorMessage ->
                        // Even if it fails, we bypass and move to the home page
                        onNextClicked()
                    }
                )
            } else {
                viewModel.nextStep()
            }
        }

    }
}


@Composable
fun DetailItem(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    isDatePicker: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .border(1.dp, color_E5E7EB, RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .then(if (isDatePicker) Modifier.clickable { onCheckedChange(true) } else Modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
            color = color_1F2937,
            modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
        )
        if (!isDatePicker) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun detailsreview() {
    DetailsScreen1()
}
