package com.example.myapplication.OnBoardingModule.featDetails.Screen

import com.example.myapplication.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import com.example.myapplication.ui.theme.color_FDF2F8
import com.example.myapplication.ui.theme.color_E5E7EB
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.components.HeadingText
import com.example.myapplication.components.PrimaryButton
import com.example.myapplication.ui.theme.color_1F2937
import com.example.myapplication.ui.theme.color_FF6B9D
import com.example.myapplication.ui.theme.color_FFE5EF
import com.example.myapplication.ui.theme.color_FFFFFF

@Composable
fun PersonalDetailsScreen1(onNextClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color_FDF2F8).padding(top=60.dp).padding(start=40.dp,end=40.dp,bottom=36.dp  )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(bottom=32.dp))
        {
            HeadingText("Welcome!", color= color_1F2937)
        }
        

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.border(1.dp, color_E5E7EB, RoundedCornerShape(24.dp))
                .background(color_FFFFFF, RoundedCornerShape(24.dp))
                .padding(top=36.dp, bottom=50.dp,start=35.dp,end=35.dp)
        )
        {
            Icon(
                painter = painterResource(R.drawable.div),
                contentDescription = "icon",
                tint = Color.Unspecified
            )
            Text(
                text = "Let's personalize your experience", 
                fontSize = 20.sp, 
                fontWeight = FontWeight.W600,
                color = color_1F2937,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top=24.dp,bottom=20.dp)
            )
            Text(
                text= "We'll ask you a few questions about your health to provide " +
                        "the best tracking experience. This will only take 2 minutes.",
                fontSize = 14.sp, 
                fontWeight = FontWeight.W400,
                color = color_1F2937,
                textAlign = TextAlign.Center
            )

        }

        Column(modifier= Modifier
            .padding(top = 24.dp)
            .background(color_FFE5EF, RoundedCornerShape(16.dp))
            .padding(top=25.dp, bottom=25.dp,start=25.dp,end=15.dp)){
            Column(){

                Row() {
                    Icon(
                        painter = painterResource(R.drawable.i),
                        contentDescription = "i",
                        tint = Color.Unspecified
                    )

                    Column(modifier= Modifier.padding(start=10.dp)) {
                        Text(text="Your privacy matters", fontSize = 14.sp,
                            fontWeight = FontWeight.W600,color= color_1F2937, modifier = Modifier.padding(bottom=7.dp))
                        Text(text="All information is encrypted and private. We never share your data.",
                            fontSize = 12.sp, fontWeight = FontWeight.W400,color= color_1F2937)
                    }

                }


            }
        }

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(text= "Let's Start", color= color_FF6B9D) { 
            onNextClicked()
        }

    }
}

@Preview(showBackground= true)
@Composable
fun screenpreview(){
    PersonalDetailsScreen1(onNextClicked = {})
}



