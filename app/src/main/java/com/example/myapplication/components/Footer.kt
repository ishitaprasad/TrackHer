package com.example.myapplication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.color_9CA3AF
import com.example.myapplication.ui.theme.color_F3F4F6
import com.example.myapplication.ui.theme.color_FBF6FC
import com.example.myapplication.ui.theme.color_FFFFFF


@Composable
fun Footer(){
    Column(modifier = Modifier.background(color_FFFFFF)) {
        HorizontalDivider(modifier = Modifier.background(color_F3F4F6))
        Row(modifier = Modifier.height(77.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround)
        {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home",
                    tint = color_9CA3AF
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text= "Home", fontSize = 11.sp,color = color_9CA3AF )
            }
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Filled.CalendarToday,
                    contentDescription = "Calendar",
                    tint = color_9CA3AF
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(text= "Calender", fontSize = 11.sp,color = color_9CA3AF )
            }
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.TrendingUp,
                    contentDescription = "Trends",
                    tint = color_9CA3AF
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text= "Trends", fontSize = 11.sp,color = color_9CA3AF )
            }
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(

                    imageVector = Icons.Filled.ChatBubbleOutline,
                    contentDescription = "Chat",
                    tint = color_9CA3AF
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(text= "Assistant", fontSize = 11.sp,color = color_9CA3AF )
            }

        }
    }

}


@Preview(showBackground = true)
@Composable
fun footerpreview(){
    Footer()
}
