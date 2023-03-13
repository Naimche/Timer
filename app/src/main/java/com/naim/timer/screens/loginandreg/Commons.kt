package com.naim.timer.screens.loginandreg

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.naim.timer.R

@Composable
fun PasswordField(onChange: (String) -> Unit) {
    var password by remember { mutableStateOf("") }

    Column() {
        TextField(
            value = password,
            onValueChange = {
                password = it
                onChange(it)
            },
            label = { Text("Contraseña", color = Color.White) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .padding(10.dp)
                .focusable(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password,
                autoCorrect = false,
                capitalization = KeyboardCapitalization.None
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFe2c0b6),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = Color(0xFF9b5de5),
                textColor = Color.White
            ),

            )
    }

}

@Composable
fun Background() {
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "background",
        modifier = Modifier
            .fillMaxSize()
            .scale(1.5f),
    )
}
@Composable
fun EmailField(onChange: (String) -> Unit) {
    var email by remember { mutableStateOf("") }
    TextField(
        value = email,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Email,
            autoCorrect = false,
            capitalization = KeyboardCapitalization.None
        ),
        onValueChange = {
            email = it
            onChange(it)
        },
        label = { Text(text = "Email", color = Color.White) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0xFFe2c0b6),

            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = Color(0xFF9b5de5),
            textColor = Color.White
        ), modifier = Modifier.focusable()
    )
}