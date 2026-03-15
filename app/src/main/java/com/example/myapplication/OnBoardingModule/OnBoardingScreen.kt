package com.example.myapplication.OnBoardingModule

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.components.HeadingText
import com.example.myapplication.components.PrimaryButton
import com.example.myapplication.ui.theme.RoyalPurple
import com.example.myapplication.ui.theme.color_FBF6FC
import com.example.myapplication.ui.theme.color_FDF2F8
import kotlinx.coroutines.delay

data class OnBoardingPage(
    val heading : String,
    val description : String
)

val OnBoardingPages = listOf(
    OnBoardingPage(
        heading = "Hello! Welcome to TrackHer ",
        description = "Your smart companion for understanding and managing your menstrual cycle."
    ),
    OnBoardingPage(
        heading = "Log Your Cycle Easily",
        description = "Record period dates, flow levels, and symptoms with a simple, intuitive interface."
    ),
    OnBoardingPage(
        heading = "Get Accurate, AI-Powered Predictions",
        description = "TrackHer analyzes your history to forecast upcoming periods and flag irregularities early."
    ),
    OnBoardingPage(
        heading = "See Your Cycle at a Glance",
        description = "Access a clean dashboard with your next predicted period, cycle stats, and alerts.")
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier, onContinueClicked: () -> Unit) {

    val pagerState = rememberPagerState{OnBoardingPages.size}

    LaunchedEffect(Unit){
        while(true){
            delay(2000)
            val nextPage = (pagerState.currentPage + 1) % OnBoardingPages.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Box(modifier
        .background(color_FDF2F8)
        .fillMaxSize().padding(start = 25.dp, end=25.dp),
        contentAlignment = Alignment.Center
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            HorizontalPager(state = pagerState,
                modifier = Modifier.padding(16.dp)
            )
            {
                    page->
                Box(modifier = Modifier
                    .fillMaxWidth(),
                    contentAlignment = Alignment.Center){


                    Column(modifier= Modifier.padding(16.dp).fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        HeadingText(OnBoardingPages[page].heading)
                      
                        Text(text=OnBoardingPages[page].description,
                            fontWeight = FontWeight.Medium, fontSize = 20.sp, textAlign = TextAlign.Center,
                            modifier= Modifier.fillMaxWidth().padding(top=15.dp, bottom = 15.dp))
                    }
                }
            }

            Navigator(pagecount = OnBoardingPages.size, currentpage = pagerState.currentPage)

            Spacer(Modifier.height(32.dp))

            PrimaryButton(text = "Continue") {
                onContinueClicked()
            }
        }
    }
}

@Composable
fun Navigator(pagecount: Int, currentpage: Int){
    Row( horizontalArrangement = Arrangement.spacedBy(8.dp))
    {
        repeat(pagecount){
                index->
            Box(
                modifier = Modifier
                    .height(6.dp)
                    .width(if (index == currentpage) 24.dp else 8.dp)
                    .background(
                        color = if (index == currentpage)
                            RoyalPurple
                        else
                            RoyalPurple.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(50)
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    OnBoardingScreen { }
}
