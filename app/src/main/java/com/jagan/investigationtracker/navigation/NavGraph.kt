package com.jagan.investigationtracker.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.jagan.investigationtracker.screens.*
import com.jagan.investigationtracker.screens.AdminScreens.AdminMainScreen
import com.jagan.investigationtracker.screens.Officer.OfficerMainScreen
import com.jagan.investigationtracker.util.SharedViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    context: Context,
    sharedViewModel:SharedViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.InitialScreen.route,
    ) {
        composable(route = Screen.InitialScreen.route) {
           InitialScreen(navController,context)
        }

        composable(route = Screen.AdminLogin.route) {
           AdminLogin(navController,context,sharedViewModel)
        }

        composable(route = Screen.AdminMainScreen.route) {
            AdminMainScreen(context,sharedViewModel)
        }

        composable(route = Screen.OfficerLogin.route) {
           OfficerLogin(navController,context,sharedViewModel)
        }

        composable(route = Screen.OfficerMainScreen.route) {
            OfficerMainScreen(navController, context, sharedViewModel)
        }

    }
}

























