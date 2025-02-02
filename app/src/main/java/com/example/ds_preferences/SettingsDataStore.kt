package com.example.ds_preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore


private const val LAYOUT_PREFERENCES_NAME = "layout_preferences"
// Create a DataStore instance using the preferencesDataStore delegate, with the Context as
// receiver.
val Context.appDataStore : DataStore<Preferences> by preferencesDataStore(
    name = LAYOUT_PREFERENCES_NAME
)

data class appPreferences(val nom:String?,val esmenor:Boolean?)

/*
class SettingsDataStore(context: Context) {
    private val nom = stringPreferencesKey("nom")
    private val esmenor = booleanPreferencesKey("esmenor")

}*/