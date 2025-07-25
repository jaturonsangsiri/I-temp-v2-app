package com.example.lgs_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lgs_app.composables.DefaultCustomComposable
import com.example.lgs_app.ui.theme.Lgs_appTheme
import androidx.navigation.compose.rememberNavController
import com.example.lgs_app.configs.Routes

val defaultCustomComposable = DefaultCustomComposable()

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      Lgs_appTheme {
        val navController = rememberNavController()
        Routes(navController)
      }
    }
  }
}