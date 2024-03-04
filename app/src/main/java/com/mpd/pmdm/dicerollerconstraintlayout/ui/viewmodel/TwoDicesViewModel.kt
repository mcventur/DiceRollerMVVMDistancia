package com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.mpd.pmdm.dicerollerconstraintlayout.core.DiceRollerApp
import com.mpd.pmdm.dicerollerconstraintlayout.data.LocalRepository
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.DiceRolls
import com.mpd.pmdm.dicerollerconstraintlayout.ui.views.Dice
import kotlinx.coroutines.launch

class TwoDicesViewModel(val numSides: Int, val repository: LocalRepository): ViewModel() {

    private val dice1 = Dice(numSides)
    private val dice2 = Dice(numSides)

    val currentSideDice1: LiveData<Int> = dice1.currentSide
    val currentSideDice2: LiveData<Int> = dice2.currentSide

    //Listado de lanzamientos
    val allDiceRolls = repository.allRolls

    fun rollDices(){
        dice1.roll()
        dice2.roll()

        //Guardamos el registro de lanzamiento en la bdd
        val resultDice1 = currentSideDice1.value
        val resultDice2 = currentSideDice2.value

        viewModelScope.launch {
            if(resultDice1 != null && resultDice2 != null){
                repository.insertRoll(resultDice1.toByte(), resultDice2.toByte())
            }
        }

    }
}

class TwoDicesViewModelFactory(val caras: Int): ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        val app = checkNotNull(extras[APPLICATION_KEY]) as DiceRollerApp
        val repo = app.localRepo
        return TwoDicesViewModel(caras, repo) as T
    }
}