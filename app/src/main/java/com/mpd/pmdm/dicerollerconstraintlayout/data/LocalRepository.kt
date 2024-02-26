package com.mpd.pmdm.dicerollerconstraintlayout.data

import androidx.lifecycle.LiveData
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.DiceRolls
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.RollsDatabase

class LocalRepository(private val dataBase: RollsDatabase) {

    private val dao = dataBase.dao()

    val allRolls: LiveData<List<DiceRolls>> = dao.getAllRolls()

    suspend fun insertRoll(dice1Result: Byte, dice2Result: Byte){
        val diceRollEntity = DiceRolls(dice1Result = dice1Result, dice2Result = dice2Result)
        dao.insertDiceRoll(diceRollEntity)
    }

}