package com.mpd.pmdm.dicerollerconstraintlayout.ui.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.mpd.pmdm.dicerollerconstraintlayout.R
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentSettingsBinding
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TwoDicesViewModel
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TwoDicesViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TwoDicesViewModel by activityViewModels{ TwoDicesViewModelFactory(6) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Cargamos el valor actual de las preferencias para actualizar el estado de la UI
        lifecycleScope.launch(Dispatchers.Main) {
//            viewModel.userPreferences.collect(){userPreferences ->
//                Log.d("SettingsFragment","Recolectando preferencias $userPreferences")
//                val checkedId = when(userPreferences.time_format){
//                    "AM_PM_format" -> R.id.AM_PM_format
//                    else -> R.id.hours_24_format
//                }
//                binding.formatoHora.check(checkedId)
//
//                binding.displayIdSwitch.isChecked = userPreferences.display_id
//            }
            val userPref = viewModel.userPreferences.first()
            Log.d("SettingsFragment","Recolectando preferencias $userPref")
            val checkedId = when(userPref.time_format){
                    "AM_PM_format" -> R.id.AM_PM_format
                    else -> R.id.hours_24_format
            }
            binding.formatoHora.check(checkedId)
            binding.displayIdSwitch.isChecked = userPref.display_id
        }

        //Actualizamos las preferencias cuando el usuario las modifique
        binding.formatoHora.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if(isChecked){
                val stringTimeFormat = when(checkedId){
                    R.id.AM_PM_format -> "AM_PM_format"
                    else -> "hours_24_format"
                }
                viewModel.setTimeFormatPreference(stringTimeFormat)
            }
        }




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}