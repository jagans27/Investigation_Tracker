
/*
package com.jagan.investigationtracker.navigation

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jagan.investigationtracker.navigation.Officer.OfficerScreen
import com.jagan.investigationtracker.screens.*
import com.jagan.investigationtracker.screens.Officer.OfficerDashboard
import com.jagan.investigationtracker.util.SharedViewModel

fun NavGraphBuilder.OfficerNavGraph(
    navController: NavHostController, context: Context, sharedViewModel: SharedViewModel
) {
    navigation(
        startDestination = OfficerScreen.OfficerLogin.route
    ) {
        composable(route = OfficerScreen.OfficerLogin.route) {
            OfficerLogin(
                navController = navController, context = context, sharedViewModel = sharedViewModel
            )
        }

        composable(route = OfficerScreen.OfficerDashboard.route) {
            OfficerDashboard(
                navController = navController, context = context, sharedViewModel = sharedViewModel
            )
        }
    }
}*/
