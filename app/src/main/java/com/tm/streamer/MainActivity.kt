package com.tm.streamer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.tm.streamer.navigation.NavRoute
import com.tm.streamer.ui.feature.home.HomeScreen
import com.tm.streamer.ui.feature.login.LoginScreen
import com.tm.streamer.ui.feature.signup.SignUpScreen
import com.tm.streamer.ui.theme.StreamerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StreamerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val controller = rememberNavController()
                    val startDestination =
                        if (FirebaseAuth.getInstance().currentUser != null) NavRoute.Home.route else NavRoute.Login.route
                    NavHost(
                        navController = controller,
                        startDestination = startDestination,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(NavRoute.Home.route) {
                            HomeScreen(navController = controller)
                        }
                        composable(NavRoute.SignUp.route) {
                            SignUpScreen(navController = controller)
                        }
                        composable(NavRoute.Login.route) {
                            LoginScreen(navController = controller)
                        }
                    }
                }
            }
        }
    }
}