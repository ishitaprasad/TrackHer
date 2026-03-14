package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MajorTheme
import com.example.myapplication.HomeModule.HomeScreen
import com.example.myapplication.onboarding.OnBoardingScreen
import com.example.myapplication.onboarding.SignInScreen
import com.example.myapplication.onboarding.RegisterScreen
import com.example.myapplication.OnBoardingModule.SplashScreen
import com.example.myapplication.OnBoardingModule.featDetails.PersonalDetailsScreen1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MajorTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splashscreen") {

        composable ("splashscreen"){
            SplashScreen (
                onTimeout = {
                    navController.navigate("onboarding") {
                        popUpTo("splashscreen") { inclusive = true }
                    }
                }
            )
        }
        composable("onboarding") {
            OnBoardingScreen(
                onContinueClicked = {
                    navController.navigate("login")
                }
            )
        }
        composable("login") {
            SignInScreen(
                onSignInClicked = {
                    navController.navigate("home")
                },
                onRegisterClicked = {
                    navController.navigate("register")
                }
            )
        }
        composable("register") {
            RegisterScreen(
                onRegisterClicked = {
                    navController.navigate("home")
                },
                onSignInClicked = {
                    navController.navigate("login")
                }
            )
        }
        composable("home") {
            HomeScreen()
        }

        composable("personal_details_1") {
            PersonalDetailsScreen1(
                onNextClicked = {
                    navController.navigate("personal_details_2")
                }
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MajorTheme {
        App()
    }
}
