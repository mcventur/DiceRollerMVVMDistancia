package com.mpd.pmdm.dicerollerconstraintlayout.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DiceRollsDao {

    @Query("SELECT * FROM dice_rolls ORDER BY id DESC")
    fun getAllRolls(): LiveData<List<DiceRolls>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiceRoll(diceRoll: DiceRolls)

    @Update
    suspend fun updateDiceRoll(diceRoll: DiceRolls)

    @Query("SELECT * FROM dice_rolls WHERE timestamp BETWEEN :sinceTimestamp AND :toTimestamp ORDER BY id DESC")
    fun getRollsBetweenTimes(sinceTimestamp: Long, toTimestamp: Long): LiveData<List<DiceRolls>>

    @Delete
    suspend fun deleteDiceRoll(diceRoll: DiceRolls)

    @Query("DELETE FROM dice_rolls")
    suspend fun clearDiceRolls()


}