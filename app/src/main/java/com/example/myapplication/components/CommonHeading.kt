package com.example.myapplication.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.color_1D008C

@Composable
fun HeadingText(text:String){
    Text(text=text, color = color_1D008C, fontSize = 28.sp,
        fontWeight = FontWeight.Companion.Bold, textAlign = TextAlign.Companion.Center, lineHeight = 36.sp)
}
