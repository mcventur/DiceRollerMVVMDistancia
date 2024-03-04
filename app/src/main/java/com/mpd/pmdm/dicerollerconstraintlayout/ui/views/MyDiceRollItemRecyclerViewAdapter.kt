package com.mpd.pmdm.dicerollerconstraintlayout.ui.views

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.DiceRolls
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentDiceRollItemBinding
import java.text.SimpleDateFormat
import java.util.Date


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyDiceRollItemRecyclerViewAdapter(
    private val values: List<DiceRolls>
) : RecyclerView.Adapter<MyDiceRollItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentDiceRollItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bindData(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val binding: FragmentDiceRollItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: DiceRolls){
            binding.idItem.text = item.id.toString()
            binding.dice1ResultItem.text = item.dice1Result.toString()
            binding.dice2ResultItem.text = item.dice2Result.toString()
            binding.rollDateItem.text = SimpleDateFormat("dd/MM/yyyy h:mm a")
                .format(Date(item.timestamp))
        }

    }

}