package com.example.footballfixtures.presentation.ui.competitions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.footballfixtures.data.remote.dto.Competition
import com.example.footballfixtures.databinding.CompetitionRvItemBinding

class CompetitionsRVAdapter(private val onItemClick: (Competition) -> Unit) :
    ListAdapter<Competition, CompetitionsRVAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(
        val binding: CompetitionRvItemBinding,
        private val onItemClick: (Competition) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(competition: Competition) {
            binding.apply {
                tvName.text = competition.name
                competitionRootLayout.setOnClickListener {
                    onItemClick.invoke(competition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CompetitionRvItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object DiffCallback : DiffUtil.ItemCallback<Competition>() {
    override fun areItemsTheSame(
        oldItem: Competition,
        newItem: Competition
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Competition,
        newItem: Competition
    ): Boolean {
        return oldItem == newItem
    }
}

