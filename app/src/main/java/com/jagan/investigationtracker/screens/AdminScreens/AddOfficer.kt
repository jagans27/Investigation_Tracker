package com.jagan.investigationtracker.screens.AdminScreens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.jagan.investigationtracker.ui.theme.Darkblue200
import com.jagan.investigationtracker.ui.theme.Darkblue300
import com.jagan.investigationtracker.ui.theme.Green100
import com.jagan.investigationtracker.util.AddOfficerData
import com.jagan.investigationtracker.util.SharedViewModel
import com.jagan.investigationtracker.util.StoreData

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddOfficer(
    navController: NavController, context: Context, sharedViewModel: SharedViewModel
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    var ph_error by rememberSaveable { mutableStateOf(false) }
    var mail_error by rememberSaveable { mutableStateOf(false) }

    var id by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var ph by rememberSaveable { mutableStateOf("") }
    var address by rememberSaveable { mutableStateOf("") }
    var mail by rememberSaveable { mutableStateOf("") }

    sharedViewModel.getTopOfficeId(officerId = {
        id = it
    }, context = context)

    val datastore = StoreData(context)
    val adminId = datastore.getAdminUserName.collectAsState(initial = "")



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Darkblue200)
            .padding(20.dp)
            .verticalScroll(rememberScrollState()), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold, color = Green100, fontSize = 40.sp
                    )
                ) {
                    append("A")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Thin, color = Color.White, fontSize = 30.sp
                    )
                ) {
                    append("dd  ")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold, color = Green100, fontSize = 40.sp
                    )
                ) {
                    append("O")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Thin, color = Color.White, fontSize = 30.sp
                    )
                ) {
                    append("fficer")
                }
            })

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "add the most suitable officer to the case",
                fontSize = 18.sp,
                color = Darkblue300,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 35.dp)

            )

            Spacer(modifier = Modifier.height(40.dp))

            TextField(
                readOnly = true,
                modifier = Modifier
                    .width(270.dp)
                    .height(53.dp)
                    .clip(
                        RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp)
                    ),
                value = "",
                placeholder = {
                    Text(text = id, fontSize = 16.sp)
                },
                onValueChange = {},
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Darkblue300,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
            )

            Spacer(modifier = Modifier.height(18.dp))

            TextField(
                modifier = Modifier
                    .width(270.dp)
                    .height(53.dp)
                    .clip(
                        RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp)
                    ),
                value = name,
                onValueChange = {
                    if (it.length <= 40) name = it
                },
                placeholder = {
                    Text(text = "name", fontSize = 16.sp)
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

            Spacer(modifier = Modifier.height(18.dp))

            TextField(modifier = Modifier
                .width(270.dp)
                .height(53.dp)
                .clip(
                    RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp)
                ), value = ph, trailingIcon = {
                if (ph_error) Icon(Icons.Filled.Warning, "enter 10 digit", tint = Green100)
            }, onValueChange = {
                if (it.length <= 10 && it.isDigitsOnly()) {
                    ph = it
                    ph_error = ph.length != 10
                }
            }, placeholder = {
                Text(text = "phone no.", fontSize = 16.sp)
            }, colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                disabledTextColor = Color.Transparent,
                backgroundColor = Darkblue300,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ), singleLine = true, keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
            )
            )
            Spacer(modifier = Modifier.height(18.dp))

            TextField(modifier = Modifier
                .width(270.dp)
                .height(53.dp)
                .clip(
                    RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp)
                ), value = address, onValueChange = {
                if (it.length <= 150) address = it
            }, placeholder = {
                Text(text = "address", fontSize = 16.sp)
            },

                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Darkblue300,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ), singleLine = true, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            Spacer(modifier = Modifier.height(18.dp))

            TextField(modifier = Modifier
                .width(270.dp)
                .height(53.dp)
                .clip(
                    RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp)
                ), value = mail, trailingIcon = {
                if (mail_error) Icon(Icons.Filled.Warning, "enter 10 digit", tint = Green100)
            }, onValueChange = {

                val emailRegex = "^[A-Za-z](.*)([@])(.+)(\\.)(.+)"
                mail_error = !emailRegex.toRegex().matches(it)
                mail = it
            }, placeholder = {
                Text(text = "mail", fontSize = 16.sp)
            }, colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                disabledTextColor = Color.Transparent,
                backgroundColor = Darkblue300,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ), singleLine = true, keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ), keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
            )
            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    if (!mail_error && !ph_error && name.isNotEmpty() && address.isNotEmpty()) {
                        val officerData = AddOfficerData(
                            id = id,
                            adminId = adminId.value!!,
                            name = name,
                            phone = ph,
                            address = address,
                            mail = mail
                        )
                        sharedViewModel.addOfficerData(officerData, context, onSuccess = {
                            if (it) {
                                id = (id.toInt() + 1).toString()
                                name = ""
                                ph = ""
                                address = ""
                                mail = ""
                            }
                        })
                    } else {
                        Toast.makeText(context, "fill the fields above", Toast.LENGTH_SHORT).show()
                    }

                }, modifier = Modifier
                    .clip(RoundedCornerShape(40.dp))
                    .width(180.dp)
                    .height(47.dp)
            ) {
                Text(
                    text = "Add Officer", color = Color.White, fontSize = 15.sp
                )
            }
        }
    }
}
