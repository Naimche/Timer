package com.naim.timer.screens.loginandreg

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

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
                .padding(6.dp),
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
    val boxSize = with(LocalDensity.current) { 300.dp.toPx() }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFF1DDE6),
                        Color(0xFFAFD7B5),
                        Color(0xFFF6B4CF),
                        Color(0xFFE9D4C6),
                        Color(0xFFFAF5F9)
                    ),
                ),
            )
    ) {
    }
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
        ), modifier = Modifier.focusable().padding(6.dp)
    )
}

@Composable
fun NameField(onChange: (String) -> Unit) {
    var name by remember { mutableStateOf("") }
    TextField(
        value = name, keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text,
            autoCorrect = false,
            capitalization = KeyboardCapitalization.Words
        ), onValueChange = {
            name = it
            onChange(it)
        },
        label = { Text(text = "Nick", color = Color.White) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0xFFe2c0b6),

            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = Color(0xFF9b5de5),
            textColor = Color.White
        ), modifier = Modifier.focusable().padding(6.dp)
    )
}

@Composable
fun MismatchDialog(onDismiss: () -> Unit, text: String, show: Boolean) {
    if (show) {
        AlertDialog(
            onDismissRequest = onDismiss,
            modifier = Modifier.width(280.dp),
            title = {
                Text(
                    text = "Error",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                )
            },
            text = {
                Text(
                    text = text,
                    style = MaterialTheme.typography.body2,
                )
            },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text(
                        text = "OK",
                        color = Color(0xFFd88870),
                        style = MaterialTheme.typography.button
                    )
                }
            },
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.surface
        )
    }
}

