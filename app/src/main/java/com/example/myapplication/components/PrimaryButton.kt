package com.example.myapplication.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.RoyalPurple
import com.example.myapplication.ui.theme.color_1D008C
import com.example.myapplication.ui.theme.color_5035B6
import com.example.myapplication.ui.theme.color_7C3AED

@Composable
fun PrimaryButton(text:String, enabled: Boolean = true, onClick: () -> Unit){
    Button(onClick =  onClick,
        enabled = enabled,
        modifier = Modifier.height(56.dp).width(287.dp),
        shape= RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color_7C3AED,
            contentColor = Color.White,

            )
    ) {
        Text(text=text, fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun buttonpreview(){
    PrimaryButton(text="hello") { }
}
