package com.example.myapplication.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.components.CommonTextField
import com.example.myapplication.components.Header
import com.example.myapplication.components.HeadingText
import com.example.myapplication.ui.theme.color_7C3AED
import com.example.myapplication.ui.theme.color_FBF6FC

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.focusModifier
import com.example.myapplication.components.PrimaryButton
import com.example.myapplication.ui.theme.color_4B5563
import com.example.myapplication.ui.theme.color_5035B6

import androidx.compose.foundation.clickable

@Composable
fun RegisterScreen(onRegisterClicked: () -> Unit, onSignInClicked: () -> Unit){
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phNo by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Box(modifier = Modifier.background(color_FBF6FC)){
        Column(
            modifier = Modifier.fillMaxSize().background(color_FBF6FC),
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.height(36.dp))
            HeadingText(text= "Create Your Account")
            Spacer(modifier = Modifier.height(2.dp))

            Column(
                modifier = Modifier.padding(24.dp).fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                Row(modifier = Modifier.fillMaxWidth()){

                    CommonTextField(
                        value=firstName,
                        onValueChange = { firstName = it },
                        label = "firstname",
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    CommonTextField(
                        value=lastName,
                        onValueChange = { lastName = it },
                        label = "lastname",
                        modifier = Modifier.weight(1f)
                    )
                }
                CommonTextField(value=email , onValueChange = { email = it }, label = "email")
                Row(modifier = Modifier.fillMaxWidth()){
                    CommonTextField(
                        value=phNo,
                        onValueChange = { phNo = it },
                        label = "phno.",
                        modifier = Modifier.weight(0.35f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    CommonTextField(
                        value=phoneNumber,
                        onValueChange = { phoneNumber = it },
                        label = "phonenumber",
                        modifier = Modifier.weight(1f)
                    )
                }
                CommonTextField(value=username , onValueChange = { username = it }, label = "username")
                CommonTextField(value=password , onValueChange = { password = it }, label = "password")
                CommonTextField(value=confirmPassword , onValueChange = { confirmPassword = it }, label = "Confirm Password")
                Spacer(modifier = Modifier.height(12.dp))
                PrimaryButton(text= "Register") { onRegisterClicked() }

                Row(modifier = Modifier.padding(top=16.dp)) {
                    Text(text= "Already have an account?", color = color_4B5563, modifier = Modifier.padding(end = 4.dp))
                    Text(text= "Sign in", color = color_5035B6, modifier = Modifier.clickable { onSignInClicked() })
                }




            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun registerscreenpreview(){
    RegisterScreen(onRegisterClicked = {}, onSignInClicked = {})
}
