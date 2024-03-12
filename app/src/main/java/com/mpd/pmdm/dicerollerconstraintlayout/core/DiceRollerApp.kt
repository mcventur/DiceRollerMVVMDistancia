package com.mpd.pmdm.dicerollerconstraintlayout.core

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.mpd.pmdm.dicerollerconstraintlayout.data.LocalRepository
import com.mpd.pmdm.dicerollerconstraintlayout.data.PreferencesRepository
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.RollsDatabase

private val Context.dsPrefs: DataStore<Preferences> by preferencesDataStore(name = "preferencias")

class DiceRollerApp: Application() {
    private val database: RollsDatabase by lazy {
        RollsDatabase.getDatabase(this)
    }

    val localRepo: LocalRepository by lazy{ LocalRepository(database) }
    private lateinit var _prefsRepo: PreferencesRepository
    val prefsRepo get() = _prefsRepo

    override fun onCreate() {
        super.onCreate()
        _prefsRepo = PreferencesRepository(dsPrefs)
    }

}