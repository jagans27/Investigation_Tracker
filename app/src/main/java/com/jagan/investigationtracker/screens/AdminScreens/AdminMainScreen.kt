package com.jagan.investigationtracker.screens.AdminScreens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.jagan.investigationtracker.Menu.AppBar
import com.jagan.investigationtracker.Menu.DrawerBody
import com.jagan.investigationtracker.Menu.DrawerHeader
import com.jagan.investigationtracker.Menu.MenuItem
import com.jagan.investigationtracker.navigation.Admin.AdminNavGraph
import com.jagan.investigationtracker.ui.theme.Darkblue200
import com.jagan.investigationtracker.util.SharedViewModel
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdminMainScreen(
    context: Context, sharedViewModel: SharedViewModel
) {

    var title by remember { mutableStateOf("Home") }
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState, topBar = {
        AppBar(onNavigationIconClick = {
            scope.launch {
                scaffoldState.drawerState.open()
            }
        }, title = title)
    }, drawerScrimColor = Color.Transparent,
        drawerContent = {

            Column(
                Modifier
                    .background(Darkblue200)
            ) {

                Box(modifier = Modifier.weight(0.3f)) {
                    DrawerHeader()
                }
                Box(modifier = Modifier.weight(0.5f)) {
                    DrawerBody(
                        modifier = Modifier
                            .background(Darkblue200)
                            .fillMaxHeight(),
                        items = listOf(
                            MenuItem(
                                route = "adminWelcomeScreen",
                                title = "Home",
                                contentDescription = "Go to add Admin Home screen",
                                icon = Icons.Default.Home
                            ),
                            MenuItem(
                                route = "adminAddOfficer",
                                title = "Add Officer",
                                contentDescription = "Go to add Officer screen",
                                icon = Icons.Default.Face
                            ),
                            MenuItem(
                                route = "adminAddCase",
                                title = "Add Case",
                                contentDescription = "Go to Settings screen",
                                icon = Icons.Default.Add
                            )
                        ),
                        onItemClick = {
                            scope.launch {
                                title = it.title
                                scaffoldState.drawerState.close()
                                navController.navigate(it.route) {

                                    navController.graph.startDestinationRoute?.let { route ->
                                        popUpTo(route) {
                                            saveState = true
                                        }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        navController = navController
                    )
                }
            }
        }) {
        AdminNavGraph(
            navController = navController,
            context = context,
            sharedViewModel = sharedViewModel
        )
    }
}


