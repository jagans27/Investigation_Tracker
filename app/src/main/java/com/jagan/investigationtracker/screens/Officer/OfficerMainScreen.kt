package com.jagan.investigationtracker.screens.Officer

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jagan.investigationtracker.util.SharedViewModel

@Composable
fun OfficerMainScreen(
    navController: NavController,
    context: Context,
    sharedViewModel: SharedViewModel
){
    Box(modifier=Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "User")
    }
}