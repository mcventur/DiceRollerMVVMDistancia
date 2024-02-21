package com.mpd.pmdm.dicerollerconstraintlayout.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "dice_rolls")
data class DiceRolls(
    @PrimaryKey(autoGenerate = true) private val  id: Int = 0,
    @ColumnInfo(name = "dice1_result") private val dice1Result: Byte,
    @ColumnInfo(name = "dice2_result") private val dice2Result: Byte,
    private val timestamp: Long
)


