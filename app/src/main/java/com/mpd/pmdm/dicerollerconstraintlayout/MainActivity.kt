package com.mpd.pmdm.dicerollerconstraintlayout

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val twoDicesViewModel: TwoDicesViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Apunto desde aquí a los dos ImageView
        val dice1Image:ImageView = binding.ivDice1
        val dice2Image:ImageView = binding.ivDice2

        //asignamos el mismo comportamiento en el onClick
        val rollButton: Button = binding.btnRoll;
        rollButton.setOnClickListener {
            twoDicesViewModel.rollDices()
        }

        twoDicesViewModel.currentSideDice1.observe(this){
            updateDiceImage(it, dice1Image)
        }

        twoDicesViewModel.currentSideDice2.observe(this){caraDado ->
            updateDiceImage(caraDado, dice2Image)
        }

    }


    /**
     * Función que crea un dado, lo tira, y muestra su valor en la IU
     */
    private fun updateDiceImage(diceSide: Int, imageViewDice: ImageView) {

        val imgDiceResource = when(diceSide){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            //Esto no se debería dar, pero me obliga al usar when como expresión
            else -> R.drawable.dice_6
        }

        imageViewDice.setImageResource(imgDiceResource)
        //Le damos una descripción a la imagen para aportar accesibilidad
        imageViewDice.contentDescription = diceSide.toString()
    }
}



