package com.example.application247

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user")

class UserRepository(context: Context) {
    private val dataStore = context.dataStore

    private val USER_NAME_KEY = stringPreferencesKey("user_name")

    suspend fun saveUserName(name: String){
        dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = name
        }
    }

    val readUserName: Flow<String> = dataStore.data.map { preferences ->
        preferences[USER_NAME_KEY] ?: "Guest"
    }
}