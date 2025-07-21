package com.example.lgs_app.configs

sealed class RoutePath(val route: String) {
    data object Login : RoutePath(route = "Login")
    data object Home : RoutePath(route = "Home")
}