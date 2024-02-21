package com.mpd.pmdm.dicerollerconstraintlayout.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface DiceRollsDao {

    @Query("SELECT * FROM dice_rolls ORDER BY id DESC LIMIT 1")
    fun getAllRolls(): LiveData<List<DiceRolls>>

}