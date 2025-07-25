package com.example.lgs_app.configs

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lgs_app.pages.LoginScreen
import com.example.lgs_app.composables.login.LoginViewModel
import com.example.lgs_app.pages.HomePage

@Composable
fun Routes(controlRoute: NavHostController) {
  val viewModel: LoginViewModel = viewModel()
  NavHost(
      controlRoute,
      startDestination = RoutePath.Login.route
  ) {
    composable(RoutePath.Login.route) { LoginScreen(viewModel, controlRoute) }
    composable(RoutePath.Home.route) { HomePage(controlRoute) }
  }
}