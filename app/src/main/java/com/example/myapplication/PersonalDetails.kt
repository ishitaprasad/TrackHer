package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.components.HeadingText
import com.example.myapplication.components.PrimaryButton

@Composable
fun PersonalDetailsScreen1(onNextClicked: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(12.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeadingText(text = "Tell us about you")

        Spacer(Modifier.height(32.dp))

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(0.875f),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(20.dp)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = age,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    age = it
                }
            },
            label = { Text("Age (years)") },
            modifier = Modifier.fillMaxWidth(0.875f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(20.dp)
        )

        Spacer(Modifier.height(32.dp))

        PrimaryButton(text = "Next") {
            onNextClicked()
        }
    }
}

@Composable
fun PersonalDetailsScreen2(onSubmitClicked: () -> Unit) {
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(12.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeadingText(text = "Tell us about you")

        Spacer(Modifier.height(32.dp))

        TextField(
            value = height,
            onValueChange = {
                if (it.all { char -> char.isDigit() || char == '.' }) {
                    height = it
                }
            },
            label = { Text("Height (cm)") },
            modifier = Modifier.fillMaxWidth(0.875f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(20.dp)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = weight,
            onValueChange = {
            },
            label = { Text("Weight (kg)") },
            modifier = Modifier.fillMaxWidth(0.875f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(20.dp)
        )

        Spacer(Modifier.height(32.dp))

        PrimaryButton(text = "Submit") {
            onSubmitClicked()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonalDetails1Preview() {
    PersonalDetailsScreen1(onNextClicked = {})
}

@Preview(showBackground = true)
@Composable
fun PersonalDetails2Preview() {
    PersonalDetailsScreen2(onSubmitClicked = {})
}
