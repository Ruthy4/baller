package com.example.footballfixtures.presentation.ui.competitions.team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballfixtures.data.remote.dto.Team
import com.example.footballfixtures.databinding.TeamRvItemBinding

class TeamAdapter(private val onItemClick: (Team) -> Unit) :
    ListAdapter<Team, TeamAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(val binding: TeamRvItemBinding, private val onItemClick: (Team) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(team: Team) {
            binding.apply {
                Glide.with(binding.root.context)
                    .load(team.crestUrl)
                    .into(imgTeamLogo)

                tvTeamName.text = team.name
                teamRootLayout.setOnClickListener {
                    onItemClick.invoke(team)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TeamRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object DiffCallback : DiffUtil.ItemCallback<Team>() {
    override fun areItemsTheSame(
        oldItem: Team,
        newItem: Team
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Team,
        newItem: Team
    ): Boolean {
        return oldItem == newItem
    }
}