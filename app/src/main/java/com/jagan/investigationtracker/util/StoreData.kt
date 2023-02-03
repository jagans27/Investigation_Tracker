package com.jagan.investigationtracker.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreData(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Data")

        val ADMIN_KEY = stringPreferencesKey("admin_username")
    }

    val getAdminUserName: Flow<String?> =
        context.dataStore.data.map {
            it[ADMIN_KEY] ?: ""
        }

    suspend fun saveAdminUserName(username:String){
        context.dataStore.edit {
            it[ADMIN_KEY] = username
        }
    }

}