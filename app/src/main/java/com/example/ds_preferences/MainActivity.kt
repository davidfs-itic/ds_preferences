package com.example.ds_preferences

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btn=findViewById<Button>(R.id.btn_save)
        val edit=findViewById<EditText>(R.id.ed_nom)
        val chk=findViewById<CheckBox>(R.id.chk_vip)
        val txtpreferencies=findViewById<TextView>(R.id.txtpreferencies)



        lifecycleScope.launch(Dispatchers.IO){
            getsavedpreferences().collect{prefs->
                withContext(Dispatchers.Main){
                    if (prefs.nom == null) {
                        txtpreferencies.text="Preferencies no posades"
                    }else{
                        txtpreferencies.text="Preferencies ja guardades ${prefs.nom}"
                    }
                }
            }
        }

        btn.setOnClickListener{
            lifecycleScope.launch(Dispatchers.IO){
                savepreferences(edit.text.toString(),chk.isChecked)
            }
        }
    }

    private suspend fun savepreferences(nom: String, menor: Boolean) {
        appDataStore.edit { preferences: MutablePreferences ->
            preferences[stringPreferencesKey("nom")]=nom
            preferences[booleanPreferencesKey("esmenor")]=menor
        }
    }
    private fun getsavedpreferences(): Flow<appPreferences> {
        return appDataStore.data.map { preferences ->
            appPreferences(
                preferences[stringPreferencesKey("nom")],
                preferences[booleanPreferencesKey("esmenor")])
        }
    }

}