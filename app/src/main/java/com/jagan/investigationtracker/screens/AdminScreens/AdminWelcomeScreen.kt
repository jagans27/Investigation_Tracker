package com.jagan.investigationtracker.screens.AdminScreens

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.navigation.NavController
import com.jagan.investigationtracker.R
import com.jagan.investigationtracker.ui.theme.*
import com.jagan.investigationtracker.ui.theme.Green100
import com.jagan.investigationtracker.util.SharedViewModel
import com.jagan.investigationtracker.util.StoreData
import kotlinx.coroutines.launch

@Composable
fun AdminWelcomeScreen(
    navController: NavController, context: Context, sharedViewModel: SharedViewModel
) {

    /* Datastore - retrieve admin username */
    val datastore = StoreData(context)
    val adminId = datastore.getAdminUserName.collectAsState(initial = "")
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Darkblue200)
            .padding(40.dp)
        ,
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
                modifier = Modifier.size(110.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Investigation Tracker",
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(30.dp))


            Text(
                text = "Welcome Mr.${adminId.value!!}",
                fontSize = 24.sp,
                fontFamily = FontFamily.SansSerif,
                color = Green100,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = "manage the case and track the progress of the case",
                fontSize = 18.sp,
                color = Darkblue300,
                modifier = Modifier.fillMaxWidth(0.8f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(60.dp))


            Button(modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .clip(RoundedCornerShape(100.dp)),
                onClick =  {
                    scope.launch {
                        datastore.saveAdminUserName("Anonymous")
                    }
                    /* restart the app */
                    val packageManager: PackageManager = context.packageManager
                    val intent: Intent = packageManager.getLaunchIntentForPackage(context.packageName)!!
                    val componentName: ComponentName = intent.component!!
                    val restartIntent: Intent = Intent.makeRestartActivityTask(componentName)
                    context.startActivity(restartIntent)
                    Runtime.getRuntime().exit(0)

                }) {
                Image(
                    painter = painterResource(id = R.drawable.logout), contentDescription = "Logout",Modifier.size(35.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Log Out",
                fontSize = 19.sp,
                fontFamily = FontFamily.SansSerif,
                color = Green100,
                fontWeight = FontWeight.Bold
            )

        }
    }
}