package com.example.footballfixtures.presentation.ui.competitions.fixtures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.footballfixtures.R
import com.example.footballfixtures.data.remote.dto.Match
import com.example.footballfixtures.data.remote.dto.Score
import com.example.footballfixtures.databinding.FixturesRvItemBinding
import com.example.footballfixtures.utils.DateTimeUtils
import com.example.footballfixtures.utils.REGULAR

class FixturesAdapter: ListAdapter<Match, FixturesAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(val binding: FixturesRvItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(match: Match) {
            binding.apply {
                tvMd.text = root.context.getString(R.string.md_text)
                tvScore.text =root.context.getString(R.string.md_text)
                tvScoreAway.text = resolveAwayScore(match.score)
                tvScoreHome.text = resolveHomeScore(match.score)
                tvStatus.text = match.status
                tvTeamAway.text = match.awayTeam?.name.toString()
                tvTeamHome.text = match.homeTeam?.name.toString()
                tvTime.text = DateTimeUtils.formatTime(match.utcDate)
            }
        }

        private fun resolveHomeScore(score: Score?): String? {
            return when (score?.duration) {
                REGULAR -> score.fullTimeScore?.homeTeamScore?.toString()
                else -> if (score?.penaltiesScore?.homeTeamScore != null) {
                    score.penaltiesScore.homeTeamScore.toString()
                } else {
                    score?.extraTimeScore?.homeTeamScore?.toString() ?: "0"
                }
            }
        }

        private fun resolveAwayScore(score: Score?): String? {
            return when (score?.duration) {
                REGULAR -> score.fullTimeScore?.awayTeamScore?.toString()
                else -> if (score?.penaltiesScore?.awayTeamScore != null) {
                    score.penaltiesScore.awayTeamScore.toString()
                } else {
                    score?.extraTimeScore?.awayTeamScore?.toString() ?: "0"
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FixturesRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}

object DiffCallback : DiffUtil.ItemCallback<Match>() {
    override fun areItemsTheSame(
        oldItem: Match,
        newItem: Match
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Match,
        newItem: Match
    ): Boolean {
        return oldItem == newItem
    }
}

