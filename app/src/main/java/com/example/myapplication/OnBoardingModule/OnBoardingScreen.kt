package com.example.myapplication.onboarding

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.components.HeadingText
import com.example.myapplication.components.PrimaryButton
import com.example.myapplication.ui.theme.RoyalPurple
import com.example.myapplication.ui.theme.color_FBF6FC
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
        heading = "Track Your Cycle",
        description = "Log symptoms easily"
    ),
    OnBoardingPage(
        heading = "Smart Insights",
        description = "Get predictions and health insights"
    )
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
        .background(color_FBF6FC)
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            HorizontalPager(state = pagerState,
                modifier = Modifier.height(220.dp)
            )
            {
                    page->
                Box(modifier = Modifier
                    .fillMaxWidth().fillMaxHeight(),
                    contentAlignment = Alignment.Center){

                    Column(modifier= Modifier.padding(16.dp).fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        HeadingText(OnBoardingPages[page].heading)
                        Spacer(Modifier.height(16.dp))
                        Text(text=OnBoardingPages[page].description,
                            fontWeight = FontWeight.Medium, fontSize = 20.sp, textAlign = TextAlign.Center,
                            modifier= Modifier.fillMaxWidth(0.75f))
                    }
                }
            }

            Spacer(Modifier.height(12.dp))
            Navigator(pagecount = OnBoardingPages.size, currentpage = pagerState.currentPage)
            Spacer(Modifier.height(50.dp))
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
