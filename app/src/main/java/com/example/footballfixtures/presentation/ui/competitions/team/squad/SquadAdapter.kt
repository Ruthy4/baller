package com.example.footballfixtures.presentation.ui.competitions.team.squad

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.footballfixtures.data.remote.dto.Squad
import com.example.footballfixtures.databinding.SquadRvItemBinding

class SquadAdapter: ListAdapter<Squad, SquadAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(val binding: SquadRvItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(squad: Squad){
            binding.apply {
                tvNumber.text = (adapterPosition + 1).toString()
                tvPosition.text = squad.position
                tvTeamName.text = squad.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SquadRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object DiffCallback : DiffUtil.ItemCallback<Squad>() {
    override fun areItemsTheSame(
        oldItem: Squad,
        newItem: Squad
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Squad,
        newItem: Squad
    ): Boolean {
        return oldItem == newItem
    }
}