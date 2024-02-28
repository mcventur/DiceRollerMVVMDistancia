package com.mpd.pmdm.dicerollerconstraintlayout.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.mpd.pmdm.dicerollerconstraintlayout.R
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentDadosBinding
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TwoDicesViewModel
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TwoDicesViewModelFactory

class DadosFragment : Fragment() {
    private var _binding: FragmentDadosBinding? = null
    private val binding get() = _binding!!
    private val twoDicesViewModel: TwoDicesViewModel by activityViewModels{
        TwoDicesViewModelFactory(6)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDadosBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Apunto desde aquí a los dos ImageView
        val dice1Image: ImageView = binding.ivDice1
        val dice2Image: ImageView = binding.ivDice2

        //asignamos el mismo comportamiento en el onClick
        val rollButton: Button = binding.btnRoll;
        rollButton.setOnClickListener {
            twoDicesViewModel.rollDices()
        }

        twoDicesViewModel.currentSideDice1.observe(viewLifecycleOwner){
            updateDiceImage(it, dice1Image)
        }

        twoDicesViewModel.currentSideDice2.observe(viewLifecycleOwner){caraDado ->
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