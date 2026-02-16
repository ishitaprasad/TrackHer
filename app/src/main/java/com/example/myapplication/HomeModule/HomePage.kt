package com.example.myapplication.HomeModule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.fillMaxWidth
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
            Spacer(modifier = Modifier.height(16.dp))
            Column (modifier= Modifier.height(460.dp)
                .background(color_FFFFFF, RoundedCornerShape(16.dp)).
                fillMaxWidth(0.87f).padding(horizontal = 12.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally){
                Spacer(modifier = Modifier.height(12.dp))
                Text(text= uiState.cycleDay, fontSize = 30.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text= uiState.dailyInsight, fontSize = 16.sp,
                    textAlign = TextAlign.Center, color= color_4B5563)


            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun headerpreview(){
    HomeScreen()
}
