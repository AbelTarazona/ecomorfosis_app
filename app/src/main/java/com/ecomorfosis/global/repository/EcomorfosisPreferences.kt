package com.ecomorfosis.global.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import com.ecomorfosis.global.retrofit.User
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by AbelTarazona on 6/12/2020
 */
class EcomorfosisPreferences(context: Context) {

    private val dataStore: DataStore<Preferences> = context.createDataStore(
        name = "app_pref"
    )

    companion object {
        val USER_SAVED = preferencesKey<String>(name = "user_saved")
        val SESSION_SAVED = preferencesKey<Boolean>(name = "session_saved")
    }

    suspend fun saveUser(user: User) {
        dataStore.edit { preferences ->
            preferences[USER_SAVED] = Gson().toJson(user)
        }
    }

    val userSaved: Flow<User> = dataStore.data
        .map { preferences ->
            Gson().fromJson(preferences[USER_SAVED], User::class.java)
        }

    suspend fun saveSession(active: Boolean) {
        dataStore.edit { preferences ->
            preferences[SESSION_SAVED] = active
        }
    }

    val sessionSaved: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[SESSION_SAVED] ?: false
        }
}