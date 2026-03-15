package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MajorTheme
import com.example.myapplication.HomeModule.Home.HomeScreen
import com.example.myapplication.OnBoardingModule.OnBoardingScreen
import com.example.myapplication.onboarding.SignInScreen
import com.example.myapplication.onboarding.RegisterScreen
import com.example.myapplication.OnBoardingModule.SplashScreen
import com.example.myapplication.OnBoardingModule.featDetails.Screen.PersonalDetailsScreen1
import com.example.myapplication.OnBoardingModule.featDetails.Screen.DetailsScreen1
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier

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

    NavHost(
        navController = navController, 
        startDestination = "splashscreen",
        modifier = Modifier.systemBarsPadding(),
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) {

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
                    navController.navigate("personal_details_1")
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

        composable("personal_details_2") {
            DetailsScreen1(
                onNextClicked = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
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
