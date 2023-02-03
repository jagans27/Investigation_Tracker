package com.jagan.investigationtracker.navigation.Admin

sealed class AdminScreen(val route:String){
    object AdminWelcomeScreen: AdminScreen ("adminWelcomeScreen")
    object AdminAddCase: AdminScreen ("adminAddCase")
    object AdminAddOfficer: AdminScreen ("adminAddOfficer")
}
