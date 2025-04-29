package com.tm.streamer.navigation

sealed class NavRoute(val route: String) {

    object Home : NavRoute("home")
    object Login : NavRoute("login")
    object SignUp : NavRoute("signup")
}