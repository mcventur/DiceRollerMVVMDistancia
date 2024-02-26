package com.mpd.pmdm.dicerollerconstraintlayout.core

import android.app.Application
import com.mpd.pmdm.dicerollerconstraintlayout.data.LocalRepository
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.RollsDatabase

class DiceRollerApp: Application() {
    private val database: RollsDatabase by lazy {
        RollsDatabase.getDatabase(this)
    }

    val localRepo: LocalRepository by lazy{ LocalRepository(database) }

}