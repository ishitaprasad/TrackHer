package com.example.myapplication.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.color_1D008C
import com.example.myapplication.ui.theme.color_7C3AED

@Composable
fun HeadingText(text: String, color: Color = color_7C3AED){
    Text(text=text, color = color, fontSize = 28.sp,
        fontWeight = FontWeight.W800, textAlign = TextAlign.Companion.Center, lineHeight = 36.sp)
}
