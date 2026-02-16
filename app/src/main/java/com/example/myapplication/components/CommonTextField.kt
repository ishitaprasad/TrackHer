package com.example.myapplication.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
            value: String="",
    onValueChange: (String) -> Unit,
    label: String ="",
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    Column(modifier = modifier.fillMaxWidth().padding(bottom = 16.dp)) {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                modifier = Modifier.padding(bottom = 4.dp),
                color = Color.Black // Or use a theme color if preferred, defaulting to Black for now based on context or theme
            )
        }
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            visualTransformation = visualTransformation,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedBorderColor = Color(0xFFD1D5DB),
                unfocusedBorderColor = Color(0xFFD1D5DB),
            ),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldPreview(){
    CommonTextField(value= "example@email.com", onValueChange = {}, label="Email")
}
