package com.jagan.investigationtracker.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jagan.investigationtracker.R
import com.jagan.investigationtracker.ui.theme.*
import com.jagan.investigationtracker.util.SharedViewModel
import com.jagan.investigationtracker.util.StoreData
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AdminLogin(
    navController: NavController, context: Context, sharedViewModel: SharedViewModel
) {

    var username by rememberSaveable { mutableStateOf("admin") }
    var password by rememberSaveable { mutableStateOf("1234") }

    /*Data Store to store username in local storage*/
    val dataStore = StoreData(context)
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
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
                modifier = Modifier
                    .width(130.dp)
                    .height(130.dp)
            )

            Spacer(modifier = Modifier.height(39.dp))

            Text(
                text = "Admin Login",
                fontSize = 24.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "please enter your username and password in the fields below",
                color = Darkblue300,
                modifier = Modifier.fillMaxWidth(0.9f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(50.dp))

            val keyboardController = LocalSoftwareKeyboardController.current
            TextField(
                modifier = Modifier
                    .width(270.dp)
                    .height(53.dp)
                    .clip(
                        RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp)
                    ),
                value = username,
                onValueChange = {
                    username = it
                    scope.launch {
                        dataStore.saveAdminUserName(username)
                    }
                },
                placeholder = {
                    Text(text = "username", fontSize = 16.sp)
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Darkblue300,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            )

            Spacer(modifier = Modifier.heightIn(20.dp))


            var passwordVisible by rememberSaveable { mutableStateOf(false) }
            TextField(
                modifier = Modifier
                    .width(270.dp)
                    .height(53.dp)
                    .clip(
                        RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp)
                    ),
                value = password,
                onValueChange = {
                    if (it.length <= 20) password = it
                },
                placeholder = {
                    Text(text = "password", fontSize = 16.sp)
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Darkblue300,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                singleLine = true,

                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible) painterResource(id = R.drawable.hide)
                    else painterResource(id = R.drawable.show)
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = image,
                            description,
                            modifier = Modifier.size(25.dp),
                            tint = Green100
                        )
                    }
                },
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
            )

            Spacer(modifier = Modifier.heightIn(29.dp))

            Button(
                onClick = {
                    if (username.isNotEmpty() && password.isNotEmpty()) {
                        sharedViewModel.adminLogin(username, password, context, navController)
                    } else {
                        Toast.makeText(context, "please fill the field", Toast.LENGTH_SHORT).show()
                    }
                }, modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
                    .clip(
                        RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp)
                    )
            ) {
                Text(
                    text = "Log in",
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}

