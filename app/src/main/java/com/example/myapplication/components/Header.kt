package com.example.myapplication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.color_1D008C
import com.example.myapplication.ui.theme.color_D1D5DB
import com.example.myapplication.ui.theme.color_F3F4F6
import com.example.myapplication.ui.theme.color_FBF6FC

@Composable
fun Header(modifier: Modifier= Modifier){
    Box(modifier = Modifier.background(color_FBF6FC)){
        Column() {
            Row(modifier= Modifier.fillMaxWidth().height(89.dp).padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween)
            {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Profile",
                    tint = Color(0xFF5035B6)
                )
                Text(text= "TrackHer", fontSize = 30.sp, fontWeight = FontWeight.W700, color = color_1D008C)
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notifications",
                    tint = Color(0xFF5035B6)

                )

            }
            HorizontalDivider(modifier= Modifier.background(color_F3F4F6))

        }


    }
}

@Preview(showBackground = true)
@Composable
fun headerpreview(){
    Header()
}
