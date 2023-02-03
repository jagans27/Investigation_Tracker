package com.jagan.investigationtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.common.base.Functions.compose
import com.google.common.base.Predicates.compose
import com.google.common.base.Suppliers.compose
import com.jagan.investigationtracker.navigation.NavGraph
import com.jagan.investigationtracker.ui.theme.*
import com.jagan.investigationtracker.util.SharedViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            InvestigationTrackerTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Darkblue200)
                )
                {
                    val context = LocalContext.current
                    val sharedViewModel: SharedViewModel by viewModels()
                    val navController = rememberNavController()
                    NavGraph(navController = navController,context=context,sharedViewModel=sharedViewModel)

                }
            }
        }
    }
}


