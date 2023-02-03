package com.jagan.investigationtracker.screens.AdminScreens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.jagan.investigationtracker.ui.theme.Darkblue100
import com.jagan.investigationtracker.ui.theme.Darkblue200
import com.jagan.investigationtracker.ui.theme.Darkblue300
import com.jagan.investigationtracker.ui.theme.Green100
import com.jagan.investigationtracker.util.AddOfficerData
import com.jagan.investigationtracker.util.SharedViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "MutableCollectionMutableState")
@Composable
fun AddCase(
    navController: NavController, context: Context, sharedViewModel: SharedViewModel
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    var case_id by rememberSaveable { mutableStateOf("") }
    var case_name by rememberSaveable { mutableStateOf("") }
    var officer_name by rememberSaveable { mutableStateOf("") }
    var case_note by rememberSaveable { mutableStateOf("") }
    var officerId by rememberSaveable { mutableStateOf("") }

    var officerData by rememberSaveable { mutableStateOf(HashMap<String, String>()) }

   sharedViewModel.getAllOfficeDetails(context, officerData = {
        officerData = it
    })

    sharedViewModel.getTopCaseId(caseId = {
        case_id = it.toString()
    }, context = context)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Darkblue200)
            .padding(10.dp)
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
                    append("C")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Thin, color = Color.White, fontSize = 30.sp
                    )
                ) {
                    append("ase")
                }
            })

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "grasp the case and work with the officer to complete it",
                fontSize = 18.sp,
                color = Darkblue300,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 35.dp)

            )

            Spacer(modifier = Modifier.height(40.dp))

            TextField(readOnly = true, modifier = Modifier
                .width(270.dp)
                .height(53.dp)
                .clip(
                    RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp)
                ), value = "", placeholder = {
                Text(text = case_id, fontSize = 16.sp)
            }, onValueChange = {}, colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                disabledTextColor = Color.Transparent,
                backgroundColor = Darkblue300,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
            )
            Spacer(modifier = Modifier.height(18.dp))

            TextField(modifier = Modifier
                .width(270.dp)
                .height(53.dp)
                .clip(
                    RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp)
                ), value = case_name, onValueChange = {
                if (it.length <= 100) case_name = it
            }, placeholder = {
                Text(text = "case name", fontSize = 16.sp)
            }, colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                disabledTextColor = Color.Transparent,
                backgroundColor = Darkblue300,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ), singleLine = true, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            Spacer(modifier = Modifier.height(18.dp))


            // try

            var expanded by remember {
                mutableStateOf(false)
            }

            // the box
            ExposedDropdownMenuBox(
                expanded = expanded, onExpandedChange = {
                    expanded = !expanded
                }, modifier = Modifier.clip(RoundedCornerShape(50.dp))
            ) {

                // text field
                TextField(modifier = Modifier
                    .width(270.dp)
                    .height(53.dp)
                    .clip(
                        RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp)
                    ), value = officerId, onValueChange = {}, placeholder = {
                    Text(text = "choose officer id", fontSize = 16.sp)
                },

                    readOnly = true, trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    }, colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        disabledTextColor = Color.Transparent,
                        backgroundColor = Darkblue300,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ), keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)


                )

                ExposedDropdownMenu(modifier = Modifier
                    .background(Green100)
                    .width(270.dp)
                    .height(153.dp),
                    expanded = expanded,
                    onDismissRequest = { expanded = false }) {
                    officerData.keys.forEach { selectedOption ->
                        DropdownMenuItem(
                            modifier = Modifier.clip(RoundedCornerShape(50.dp)),
                            onClick = {
                                officer_name = officerData[selectedOption].toString()
                                officerId = selectedOption
                                expanded = false
                            }) {
                            Text(text = selectedOption)
                        }
                    }
                }
            }

            // try

            Spacer(modifier = Modifier.height(18.dp))
            TextField(
                modifier = Modifier
                    .width(270.dp)
                    .height(53.dp)
                    .clip(
                        RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp)
                    ),
                value = officer_name,
                onValueChange = { },
                readOnly = true,
                placeholder = {
                    Text(
                        text = "officer name", fontSize = 16.sp
                    )
                }, colors = TextFieldDefaults.textFieldColors(
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
                .height(113.dp)
                .clip(
                    RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp)
                ), value = case_note, onValueChange = {
                if (it.length <= 250) case_note = it
            },
                placeholder = {
                Text(text = "case note", fontSize = 16.sp)
            },
                colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                disabledTextColor = Color.Transparent,
                backgroundColor = Darkblue300,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ), keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
            )


            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {

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