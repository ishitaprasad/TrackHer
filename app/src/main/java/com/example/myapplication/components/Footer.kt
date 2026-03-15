package com.example.myapplication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.myapplication.ui.theme.color_7C3AED
import com.example.myapplication.ui.theme.color_9CA3AF
import com.example.myapplication.ui.theme.color_E5E7EB
import com.example.myapplication.ui.theme.color_F3F4F6
import com.example.myapplication.ui.theme.color_FBF6FC
import com.example.myapplication.ui.theme.color_FCF7FB
import com.example.myapplication.ui.theme.color_FFFFFF


@Composable
fun Footer(selectedIndex: Int = 0){
    Column(modifier = Modifier.background(color_FCF7FB)) {
//        HorizontalDivider(color = color_E5E7EB, thickness = 1.dp)
        Row(modifier = Modifier.padding(top=10.dp,bottom=10.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround)
        {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home",
                    tint = if (selectedIndex == 0) color_7C3AED else color_9CA3AF
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text= "Home", fontSize = 11.sp,color = if (selectedIndex == 0) color_7C3AED else color_9CA3AF )
            }
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Filled.CalendarToday,
                    contentDescription = "Calendar",
                    tint = if (selectedIndex == 1) color_7C3AED else color_9CA3AF
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(text= "Calender", fontSize = 11.sp,color = if (selectedIndex == 1) color_7C3AED else color_9CA3AF )
            }
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.TrendingUp,
                    contentDescription = "Trends",
                    tint = if (selectedIndex == 2) color_7C3AED else color_9CA3AF
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text= "Trends", fontSize = 11.sp,color = if (selectedIndex == 2) color_7C3AED else color_9CA3AF )
            }
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(

                    imageVector = Icons.Filled.ChatBubbleOutline,
                    contentDescription = "Chat",
                    tint = if (selectedIndex == 3) color_7C3AED else color_9CA3AF
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(text= "Assistant", fontSize = 11.sp,color = if (selectedIndex == 3) color_7C3AED else color_9CA3AF )
            }

        }
    }

}


@Preview(showBackground = true)
@Composable
fun footerpreview(){
    Footer()
}
