package com.jagan.investigationtracker.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jagan.investigationtracker.R
import com.jagan.investigationtracker.navigation.Screen
import com.jagan.investigationtracker.ui.theme.Darkblue300

@Composable
fun InitialScreen(
    navController: NavHostController,
    context: Context
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()).padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logobig),
                contentDescription = "app logo",
                modifier = Modifier.size(130.dp)
            )

            Spacer(modifier = Modifier.height(39.dp))

            Text(
                text = "Investigation Tracker",
                fontSize = 24.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "identify the crime and win the case by tracking it down",
                color = Darkblue300,
                modifier = Modifier.fillMaxWidth(0.8f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(69.dp))


            Button(onClick = {
                navController.navigate(Screen.AdminLogin.route)
            }, modifier = Modifier
                .width(210.dp)
                .height(50.dp)
                .clip(
                    RoundedCornerShape(0.dp, 20.dp, 0.dp, 20.dp)
                )
            ) {
                Text(
                    text = "Admin",
                    fontSize = 13.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.heightIn(29.dp))

            Button(
                onClick = {
                          navController.navigate(Screen.OfficerLogin.route)
                }, modifier = Modifier
                    .width(210.dp)
                    .height(50.dp)
                    .clip(
                        RoundedCornerShape(0.dp, 20.dp, 0.dp, 20.dp)
                    )
            ) {
                Text(
                    text = "Officer",
                    fontSize = 13.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}

