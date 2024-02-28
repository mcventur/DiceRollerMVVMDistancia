package com.mpd.pmdm.dicerollerconstraintlayout.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentDiceRollListBinding

/**
 * A fragment representing a list of Items.
 */
class DiceRollListFragment : Fragment() {

    private var columnCount = 1
    private var _binding: FragmentDiceRollListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiceRollListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Set the adapter
        val view = binding.list
        with(view) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = MyDiceRollItemRecyclerViewAdapter(emptyList())
        }

    }
}