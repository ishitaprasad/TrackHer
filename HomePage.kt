package com.example.myapplication.HomeModule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.components.Footer
import com.example.myapplication.components.Header
import com.example.myapplication.ui.theme.color_4B5563
import com.example.myapplication.ui.theme.color_FBF6FC
import com.example.myapplication.ui.theme.color_FFFFFF

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.myapplication.R
import com.example.myapplication.ui.theme.color_7C3AED
import com.example.myapplication.ui.theme.color_FF6B9D
import com.example.myapplication.ui.theme.color_FF9EB3

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()){
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = color_FBF6FC,
        topBar = { Header() },
        bottomBar = { Footer() }
    ) {
            innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Column(
                modifier= Modifier.padding(top=35.dp,start=26.dp,end=26.dp))
            {
                Column(
                    modifier = Modifier
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(24.dp))
                        .background(color_FFFFFF, RoundedCornerShape(24.dp))
                        .padding(start=32.dp, end=32.dp, top=32.dp, bottom=32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(modifier = Modifier.fillMaxWidth().padding(bottom=16.dp),horizontalArrangement = Arrangement.Center){
                        Text(text="Today", fontSize = 30.sp, fontWeight = FontWeight.W800, color = color_FF6B9D)
                    }

                    Row(modifier= Modifier.padding(bottom=24.dp),horizontalArrangement = Arrangement.Center){
                        Text(text="Your energy levels are rising. It's a great time for creative projects and high-intensity workouts.",
                            fontSize = 16.sp, fontWeight = FontWeight.W400, color = color_4B5563, textAlign = TextAlign.Center)

                    }

                    Row(modifier= Modifier.background(color_FF9EB3,
                        RoundedCornerShape(8.dp)).padding(vertical = 8.dp, horizontal = 20.dp),horizontalArrangement = Arrangement.Center)
                    {
                        Text(text="Edit Period dates", fontSize = 12.sp, fontWeight = FontWeight.W600,color= color_FFFFFF)

                    }
                }

                Spacer(modifier = Modifier.padding(vertical = 12.dp))


                Column(
                    modifier= Modifier.fillMaxWidth().
                    background(color_FFFFFF,RoundedCornerShape(24.dp)).
                    padding(top=20.dp,start=25.dp,end=25.dp,bottom =20.dp), verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Row(modifier= Modifier.fillMaxWidth().padding(bottom=12.dp), horizontalArrangement = Arrangement.Start)
                    {
                        Icon(
                            painter = painterResource(R.drawable.calender),
                            contentDescription = "calendar",
                            tint = androidx.compose.ui.graphics.Color.Unspecified,
                            modifier = Modifier.padding(end=6.dp)
                        )
                        Text(text="Next Period", fontSize = 14.sp, fontWeight = FontWeight.W700, color = color_7C3AED)
                    }

                    Row(modifier= Modifier.fillMaxWidth().padding(bottom=10.dp),horizontalArrangement = Arrangement.Start){
                        Text(text="Feb 20, 2026", fontSize = 24.sp, fontWeight = FontWeight.W800, color = color_4B5563)
                    }

                    Row(modifier= Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Start){
                        Text(text="Predicted with 87% accuracy", fontWeight = FontWeight.W400, fontSize = 14.sp)


                    }



                }


            }








        }
    }
}

@Preview(showBackground = true)
@Composable
fun headerpreview(){
    HomeScreen()
}
