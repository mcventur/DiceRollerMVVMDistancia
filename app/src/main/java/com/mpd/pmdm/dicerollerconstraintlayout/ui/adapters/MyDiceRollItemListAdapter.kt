package com.mpd.pmdm.dicerollerconstraintlayout.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.DiceRolls
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentDiceRollItemBinding
import java.text.SimpleDateFormat
import java.util.Date

class MyDiceRollItemListAdapter()
    : ListAdapter<DiceRolls, MyDiceRollItemListAdapter.ViewHolder>(DiffUtilDiceRolls) {

        private var AM_PM_format: Boolean = true

    private companion object{
        private val DiffUtilDiceRolls = object: DiffUtil.ItemCallback<DiceRolls>(){
            override fun areItemsTheSame(oldItem: DiceRolls, newItem: DiceRolls): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DiceRolls, newItem: DiceRolls): Boolean {
                return oldItem == newItem
            }

        }
    }



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
        holder.bindData(getItem(position))
    }

    inner class ViewHolder(val binding: FragmentDiceRollItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: DiceRolls){
            binding.idItem.text = item.id.toString()
            binding.dice1ResultItem.text = item.dice1Result.toString()
            binding.dice2ResultItem.text = item.dice2Result.toString()

            val formatoFecha: String = if(AM_PM_format) "dd/MM/yyyy h:mm a" else "dd/MM/yyyy hh:mm"
            binding.rollDateItem.text = SimpleDateFormat(formatoFecha)
                .format(Date(item.timestamp))
        }
    }

    fun setAM_PM_Format(newValue: Boolean){
        AM_PM_format = newValue
        notifyDataSetChanged()
    }

}