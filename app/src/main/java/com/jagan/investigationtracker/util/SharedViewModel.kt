package com.jagan.investigationtracker.util

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.jagan.investigationtracker.navigation.Admin.AdminScreen
import com.jagan.investigationtracker.navigation.Officer.OfficerScreen
import com.jagan.investigationtracker.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    fun adminLogin(
        username: String,
        password: String,
        context: Context,
        navController: NavController
    ) =
        CoroutineScope(Dispatchers.IO).launch {

            val firestoreRef = Firebase.firestore
                .collection("Admin")
                .document(username)
            try {
                firestoreRef.get().addOnSuccessListener {
                    if (it.exists()) {
                        val userData = it.toObject<AdminOfficerData>()!!
                        if (userData.password == password) {
                            Toast.makeText(context, "welcome $username", Toast.LENGTH_SHORT).show()
                            navController.backQueue.clear() // clear the stack
                            navController.navigate(Screen.AdminMainScreen.route)
                        } else {
                            Toast.makeText(context, "password incorrect", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "username not exist", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }

    fun userLogin(
        username: String,
        password: String,
        context: Context,
        navController: NavController
    ) =
        CoroutineScope(Dispatchers.IO).launch {
            val firestoreRef = Firebase.firestore
                .collection("User")
                .document(username)
            try {
                firestoreRef.get().addOnSuccessListener {
                    if (it.exists()) {
                        val userData = it.toObject<AdminOfficerData>()!!
                        if (userData.password == password) {
                            Toast.makeText(context, "welcome $username", Toast.LENGTH_SHORT).show()

                            navController.backQueue.clear() // clear the stack
                            navController.navigate(Screen.OfficerMainScreen.route)
                        } else {
                            Toast.makeText(context, "password incorrect", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "username not exist", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }

    fun addOfficerData(
        officerData: AddOfficerData,
        context: Context,
        onSuccess: (Boolean) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {

        val firestoreRef = Firebase.firestore
            .collection("Officer").document(officerData.id)

        val firestoreRefForId = Firebase.firestore
            .collection("IdUtil").document("officer_id")

        val oid = OfficerRetrieveId(id_start = officerData.id.toInt() + 1)

        try {
            firestoreRef.set(officerData).addOnSuccessListener {
                firestoreRefForId.set(oid).addOnSuccessListener {
                    onSuccess(true)
                    Toast.makeText(
                        context,
                        "Officer ${officerData.name} Added Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        } catch (e: Exception) {
            onSuccess(false)
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }


    fun getTopOfficeId(
        officerId: (String) -> Unit,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {
        Firebase.firestore
            .collection("IdUtil")
            .document("officer_id").addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                value!!.getString("id_start")?.let {
                    officerId(it)
                }
            }
    }


    fun getTopCaseId(
        caseId: (Int) -> Unit,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {
        Firebase.firestore
            .collection("IdUtil")
            .document("case_id").addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                value!!.getString("id_start")?.let {
                    caseId(it.toInt())
                }
            }
    }

    private val officersDetails = HashMap<String, String>()

    fun getAllOfficeDetails(
        context: Context,
        officerData: (HashMap<String, String>) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        try {

            Firebase.firestore.collection("Officer")
                .addSnapshotListener { snapshots, e ->
                    if (e != null) {
                        Log.w(TAG, "listen:error", e)
                        return@addSnapshotListener
                    }

                    for (dc in snapshots!!.documentChanges) {
                        when (dc.type) {
                            DocumentChange.Type.ADDED -> {
                                officersDetails[dc.document.data["id"].toString()] =
                                    dc.document.data["name"].toString()
                                Log.d(TAG, "New city: ${dc.document.data}")
                            }
                            DocumentChange.Type.MODIFIED -> TODO()
                            DocumentChange.Type.REMOVED -> TODO()
                        }
                    }
                    officerData(officersDetails)
                }

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}

