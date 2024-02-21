package com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mpd.pmdm.dicerollerconstraintlayout.ui.views.Dice

class TwoDicesViewModel(val numSides: Int): ViewModel() {

    private val dice1 = Dice(numSides)
    private val dice2 = Dice(numSides)

    val currentSideDice1: LiveData<Int> = dice1.currentSide
    val currentSideDice2: LiveData<Int> = dice2.currentSide

    fun rollDices(){
        dice1.roll()
        dice2.roll()
    }
}

class TwoDicesViewModelFactory(val caras: Int): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if(modelClass.isAssignableFrom(TwoDicesViewModel::class.java))
            return TwoDicesViewModel(caras) as T
        throw IllegalArgumentException("ModelClass is not an instance of TwoDicesViewModel")
    }
}