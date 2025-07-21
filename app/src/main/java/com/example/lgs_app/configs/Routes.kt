package com.example.lgs_app.configs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lgs_app.pages.HomePage
import com.example.lgs_app.pages.LoginPage

@Composable
fun Routes(controlRoute: NavHostController, paddingValue: PaddingValues) {
  NavHost(
      controlRoute,
      startDestination = RoutePath.Login.route,
      modifier = Modifier.padding(paddingValue)
  ) {
      composable(RoutePath.Login.route) { LoginPage(controlRoute) }
  }
}