package com.jagan.investigationtracker.navigation.Officer

sealed class OfficerScreen(val route:String){
    object OfficerLogin: OfficerScreen("officerLogin")
    object OfficerDashboard: OfficerScreen("officer_DashBoard")
    object AddOfficer: OfficerScreen("officer_AddOfficer")
    object AddCase: OfficerScreen("officer_AddCase")
}

