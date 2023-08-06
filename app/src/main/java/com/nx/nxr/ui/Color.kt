package com.nx.nxr.ui

import android.annotation.SuppressLint
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Color10 = Color(0xFF0B090C)
val Color50 = Color(0xFF2979FF)
val Color100 = Color(0xFF0B090C)
val Color300 = Color(0xFF6DB6B7)
val Color500 = Color(0xFF6DB6B7)
val Color901 = Color(0xFF0B090C)
val Color902 = Color(0xFF6DB6B7)
val Color903 = Color(0xFFA7A7A7)
val Color904 = Color(0xFF292929)

val Color1000 = Color(0xFF0B090C)
val Color1001 = Color(0xFF0B090C)
val Color1002 = Color(0xFF0B090C)

@SuppressLint("ConflictingOnColor")
val LightColorPalette = lightColors(
    primary = Color300,
    primaryVariant = Color500,
    secondary = Color50,
    background = Color100,
    surface = Color10,
    error = Color902,
    onPrimary = Color1000,
    onSecondary = Color901,
    onBackground = Color902,
    onSurface = Color903,
    onError = Color904
)