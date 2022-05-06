package com.example.footballfixtures.presentation.ui.competitions.team.squad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.footballfixtures.R
import com.example.footballfixtures.data.mappers.SquadDomainMapper
import com.example.footballfixtures.data.remote.dto.Squad
import com.example.footballfixtures.data.remote.dto.Table
import com.example.footballfixtures.databinding.ActivityCompetitionsDetailBinding
import com.example.footballfixtures.databinding.ActivitySquadBinding
import com.example.footballfixtures.utils.Resource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SquadActivity : AppCompatActivity() {
    lateinit var binding: ActivitySquadBinding
    private val squadViewModel: SquadViewModel by viewModels()
    lateinit var squadAdapter: SquadAdapter
    private var teamId: Int? = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySquadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeSavedSquadFromDatabase()

        squadAdapter = SquadAdapter()
        val intent = intent
        teamId = intent?.getIntExtra("teamId", 0)
        val teamLogo = intent?.getStringExtra("teamLogo")
        val teamTitle = intent?.getStringExtra("teamName")

        binding.tvTitle.text = teamTitle

        Glide.with(this)
            .load(teamLogo)
            .placeholder(R.drawable.ic_soccer)
            .into(binding.imgTeamLogo)

        squadViewModel.getTeamForSquad(teamId)
        squadViewModel.getTeamsSquadFromDatabase(teamId)

        // implement swipe to refresh
        binding.competitionFragmentSwipeRefreshLayout.setOnRefreshListener {
            squadViewModel.getTeamForSquad(teamId)
            binding.competitionFragmentSwipeRefreshLayout.isRefreshing = false
        }

    }

    private fun observeSquadResponse() {
        squadViewModel.squad.observe(this) {
            when (it) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    val tablesList: List<Squad>? = it.value.squad
                    squadViewModel.saveTeamsSquadToDatabase(
                        (SquadDomainMapper(
                            tablesList,
                            teamId
                        )).squadDomain
                    )
                }

                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    Snackbar.make(binding.appbarLayout, it.error, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }

                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }
        }
    }

    // read from room database
    private fun observeSavedSquadFromDatabase() {
        squadViewModel.squad.observe(this) {
            when (it) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    val tablesList: List<Squad>? = it.value.squad
                    val squadRv = binding.squadRv
                    squadRv.adapter = squadAdapter
                    squadAdapter.submitList(tablesList)
                }

                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    Snackbar.make(binding.appbarLayout, it.error, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }

                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }
        }
    }
}