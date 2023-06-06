package com.naim.timer.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.naim.timer.R

// Set of Material typography styles to start with
val Lobster = FontFamily(
    Font(R.font.lobster_regular, FontWeight.Normal)
)

val Poppins = FontFamily(
    Font(R.font.poppins, FontWeight.SemiBold),
)
val Roboto = FontFamily(
    Font(R.font.roboto, FontWeight.SemiBold),
)
val RobotoBold = FontFamily(
    Font(R.font.robotobold, FontWeight.SemiBold),
)
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)