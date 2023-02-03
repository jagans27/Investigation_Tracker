package com.jagan.investigationtracker.navigation


sealed class Screen(val route:String){
    object InitialScreen:Screen("initialScreen")
    object AdminLogin:Screen("adminLogin")
    object AdminMainScreen:Screen("adminMainScreen")
    object OfficerLogin:Screen("officerLogin")
    object OfficerMainScreen:Screen("officerMainScreen")
}

