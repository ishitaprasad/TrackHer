package com.example.myapplication.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import com.example.myapplication.components.CommonTextField
import com.example.myapplication.components.HeadingText
import com.example.myapplication.components.PrimaryButton
import com.example.myapplication.ui.theme.color_4B5563
import com.example.myapplication.ui.theme.color_5035B6
import com.example.myapplication.ui.theme.color_FBF6FC

import androidx.compose.foundation.clickable

@Composable
fun SignInScreen(onSignInClicked: () -> Unit, onRegisterClicked: () -> Unit){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier.background(color_FBF6FC)){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            HeadingText(
                text = "Explore Trends and Ask Anything"
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text="View cycle visualizations and chat with our AI assistant for personalized health insights.",
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(vertical = 18.dp))
            HeadingText(
                text = "Welcome !"
            )
            Text(text= "Sign in to continue", modifier = Modifier.padding(top=4.dp))

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            CommonTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email"
            )

            CommonTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.padding(bottom = 32.dp) // Override padding if needed or accept default
            )

            PrimaryButton(
                text = "Sign In",
                onClick = { onSignInClicked() }
            )

            Text(text= "Forgot password?", modifier = Modifier.padding(top=12.dp, bottom = 8.dp),
                color = color_4B5563)

            Row(modifier = Modifier.padding(top=16.dp)) {
                Text(text= "Don't have an account?", color = color_4B5563, modifier = Modifier.padding(end = 4.dp))
                Text(text= "Register", color = color_5035B6, modifier = Modifier.clickable { onRegisterClicked() })
            }

        }

    }


}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    SignInScreen(onSignInClicked = {}, onRegisterClicked = {})
}
