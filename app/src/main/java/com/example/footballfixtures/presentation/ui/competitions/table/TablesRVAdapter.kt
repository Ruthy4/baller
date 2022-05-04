package com.example.footballfixtures.presentation.ui.competitions.table

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballfixtures.data.remote.dto.Table
import com.example.footballfixtures.databinding.TablesRvItemBinding

class TablesRVAdapter() : ListAdapter<Table, TablesRVAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(val binding: TablesRvItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(table: Table) {
            binding.apply {
                tvNumber.text = table.position.toString()
                tvGamesPlayed.text = table.playedGames.toString()
                tvGoals.text = table.goalDifference.toString()
                tvPoints.text = table.points.toString()
                tvTeamName.text = table.team.name.toString()

                Glide.with(binding.root.context)
                    .load(table.team.crestUrl)
                    .into(imgTeamLogo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TablesRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object DiffCallback : DiffUtil.ItemCallback<Table>() {
    override fun areItemsTheSame(
        oldItem: Table,
        newItem: Table
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Table,
        newItem: Table
    ): Boolean {
        return oldItem == newItem
    }
}

