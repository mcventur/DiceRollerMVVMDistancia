package com.mpd.pmdm.dicerollerconstraintlayout

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TwoDicesViewModel(): ViewModel() {

    private val dice1 = Dice(6)
    private val dice2 = Dice(6)

    val currentSideDice1: LiveData<Int> = dice1.currentSide
    val currentSideDice2: LiveData<Int> = dice2.currentSide

    fun rollDices(){
        dice1.roll()
        dice2.roll()
    }
}