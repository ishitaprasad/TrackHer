package com.example.myapplication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
import com.example.myapplication.ui.theme.color_FF6B9D

//@Composable
//fun PrimaryButton(text:String, enabled: Boolean = true, color: Color = color_7C3AED, onClick: () -> Unit){
//    Button(onClick =  onClick,
//        enabled = enabled,
//        modifier = Modifier.fillMaxWidth().height(52.dp),
//        shape= RoundedCornerShape(20.dp),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = color,
//            contentColor = Color.White,
//
//            )
//    ) {
//        Text(text=text, fontSize = 18.sp)
//    }
//}

@Composable
fun PrimaryButton(
    text: String,
    enabled: Boolean = true,
    color: Color? = null,
    onClick: () -> Unit
) {

    val gradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFC084FC),
            Color(0xFFFF6B9D)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(
                brush = if (color == null) gradient else Brush.horizontalGradient(listOf(color, color)),
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Button(
            onClick = onClick,
            enabled = enabled,
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White
            )
        ) {
            Text(
                text = text,
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun buttonpreview(){
    PrimaryButton(text="hello") { }
}
