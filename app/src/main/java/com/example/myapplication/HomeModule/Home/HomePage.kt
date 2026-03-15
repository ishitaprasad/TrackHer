package com.example.myapplication.HomeModule.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
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
import com.example.myapplication.ui.theme.color_FFFFFF

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.myapplication.HomeModule.HomeViewModel
import com.example.myapplication.R
import com.example.myapplication.ui.theme.color_1F2937
import com.example.myapplication.ui.theme.color_7C3AED
import com.example.myapplication.ui.theme.color_FCF7FB
import com.example.myapplication.ui.theme.color_FF6B9D
import com.example.myapplication.ui.theme.color_FF9EB3

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()){
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = color_FCF7FB,
        topBar = { Header() },
        bottomBar = { Footer(selectedIndex = 0) }
    ) {
            innerPadding ->
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize().verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Column(
                modifier= Modifier.padding(top=28.dp,start=26.dp,end=26.dp))
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
                    Row(modifier= Modifier.fillMaxWidth().padding(bottom=12.dp), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically)
                    {
                        Icon(
                            painter = painterResource(R.drawable.calender),
                            contentDescription = "calendar",
                            tint = Color.Unspecified,
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

                //previous cycle dates
                Column(modifier= Modifier.padding(top=25.dp,bottom=25.dp).background(color_FFFFFF, RoundedCornerShape(24.dp)).fillMaxWidth()) {
                    Row(modifier= Modifier.fillMaxWidth().padding(top=20.dp,start=25.dp,end=25.dp, bottom = 15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                        Row(verticalAlignment = Alignment.CenterVertically){
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.TrendingUp,
                                contentDescription = "Trending",
                                tint = color_7C3AED
                            )
                            Text(text="Cycle Average", fontSize = 13.sp,
                                fontWeight = FontWeight.W700, color = color_4B5563, modifier= Modifier.padding(start=10.dp))
                        }


                        Text(text="See history", fontSize = 14.sp,
                            fontWeight = FontWeight.W400, color = color_4B5563)
                    }

                    Row(modifier= Modifier.fillMaxWidth().padding(start=25.dp,end=25.dp)){
                        Text(text="28 Days", fontSize = 30.sp, fontWeight = FontWeight.W800, color = color_1F2937)
                    }
                    
                    // mapped previous cycles
                    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp)) {
                        uiState.history.forEach { cycle ->
                            CycleHistoryRow(
                                dateRange = cycle.dateRange,
                                lengthDays = cycle.lengthDays
                            )
                        }
                    }


                }

            }

        }
    }
}

@Composable
fun CycleHistoryRow(dateRange: String, lengthDays: Int) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp, top=12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = dateRange,
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            color = color_4B5563
        )
        Text(
            text = "$lengthDays days",
            fontSize = 14.sp,
            fontWeight = FontWeight.W600,
            color = color_1F2937
        )
    }
}

@Preview(showBackground = true)
@Composable
fun headerpreview(){
    HomeScreen()
}
