
package com.jagan.investigationtracker.navigation.Admin

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jagan.investigationtracker.navigation.Screen
import com.jagan.investigationtracker.screens.AdminLogin
import com.jagan.investigationtracker.screens.AdminScreens.AddCase
import com.jagan.investigationtracker.screens.AdminScreens.AddOfficer
import com.jagan.investigationtracker.screens.AdminScreens.AdminMainScreen
import com.jagan.investigationtracker.screens.AdminScreens.AdminWelcomeScreen
import com.jagan.investigationtracker.screens.InitialScreen
import com.jagan.investigationtracker.screens.Officer.OfficerMainScreen
import com.jagan.investigationtracker.screens.OfficerLogin
import com.jagan.investigationtracker.util.SharedViewModel

@Composable
fun AdminNavGraph(
    navController: NavHostController,
    context: Context,
    sharedViewModel:SharedViewModel
) {
    NavHost(
        navController = navController,
        startDestination = AdminScreen.AdminWelcomeScreen.route,
    ) {
        composable(route = AdminScreen.AdminWelcomeScreen.route) {
            AdminWelcomeScreen(navController = navController, context = context, sharedViewModel = sharedViewModel)
        }

        composable(route = AdminScreen.AdminAddCase.route) {
            AddCase(navController = navController, context = context, sharedViewModel = sharedViewModel)
        }

        composable(route = AdminScreen.AdminAddOfficer.route) {
            AddOfficer(navController = navController, context = context, sharedViewModel = sharedViewModel)
        }

    }
}

