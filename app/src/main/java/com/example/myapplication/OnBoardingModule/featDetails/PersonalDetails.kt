package com.example.myapplication.OnBoardingModule.featDetails

import com.example.myapplication.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import com.example.myapplication.ui.theme.color_FDF2F8
import com.example.myapplication.ui.theme.color_E5E7EB
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.components.HeadingText
import com.example.myapplication.ui.theme.color_FFE5EF

@Composable
fun PersonalDetailsScreen1(onNextClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_FDF2F8).padding(top=60.dp)
    ) {
        
        HeadingText("Welcome!")

        Column(
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            modifier = Modifier.border(1.dp, color_E5E7EB, RoundedCornerShape(24.dp))
        )
        {
            Icon(painter = painterResource(R.drawable.div), contentDescription = "icon")

            Text(text= "Let's personalize your experience")
            Text(text= "We'll ask you a few questions about your health to provide the best tracking experience. This will only take 2 minutes.")

        }

        Column(modifier= Modifier.background(color_FFE5EF, RoundedCornerShape(8.dp)).padding(top=25.dp, bottom=25.dp,start=15.dp,end=15.dp)){
            Column(){
                Text(text="Your privacy matters", fontSize = 14.sp, fontWeight = FontWeight.W400)
                Text(text="All information is encrypted and private. We never share your data.",
                    fontSize = 12.sp, fontWeight = FontWeight.W400)

            }
        }

    }
}

@Preview(showBackground= true)
@Composable
fun screenpreview(){
    PersonalDetailsScreen1(onNextClicked = {})
}



