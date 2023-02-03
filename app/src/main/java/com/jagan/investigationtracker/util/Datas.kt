package com.jagan.investigationtracker.util

data class AdminOfficerData(
    val password:String="",
    val username:String=""
)

data class AddOfficerData(
    val adminId:String="",
    val id :String="",
    val name :String="",
    val phone :String="",
    val address :String="",
    val mail :String=""
)

data class OfficerRetrieveId(
    val id_start:Int = 0
)

data class CaseRetrieveId(
    val id_start:Int = 0
)