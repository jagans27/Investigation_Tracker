package com.jagan.investigationtracker.Menu

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val route:String,
    var title:String,
    val contentDescription:String,
    val icon: ImageVector
)
