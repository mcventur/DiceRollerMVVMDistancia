package com.mpd.pmdm.dicerollerconstraintlayout.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class PreferencesRepository(val dataStore: DataStore<Preferences>) {
    //Constantes con las claves de cada valor individual del datastore
    private object PreferencesKeys {
        val TIME_FORMAT = stringPreferencesKey("time_format")
        val DISPLAY_ID = booleanPreferencesKey("display_id")
    }

    private object DefaultValues {
        val TIME_FORMAT = "AM_PM_format"
        val DISPLAY_ID = true
    }


    /**
     * Lectura de los datos del DataStore como Flow mapeado a Flow de UserPreferences
     */

    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d("PreferencesRepository", "IOException al leer el DataStore", exception)
                emit(emptyPreferences())
            } else {
                exception.printStackTrace()
            }

        }
        .map { preferences ->
            val time_format = preferences[PreferencesKeys.TIME_FORMAT] ?: DefaultValues.TIME_FORMAT
            val display_id = preferences[PreferencesKeys.DISPLAY_ID] ?: DefaultValues.DISPLAY_ID
            UserPreferences(time_format, display_id)
        }


    /**
     * Para escribir la preferencia Time_Format
     */
    suspend fun setTimeFormat(timeFormat: String){
        try{
            dataStore.edit { preferences ->
                preferences[PreferencesKeys.TIME_FORMAT] = timeFormat
                Log.d("PreferencesRepository", "Escribiendo TimeFormat $timeFormat")
            }
        }catch(e: Exception){
            e.printStackTrace()
        }
    }




}