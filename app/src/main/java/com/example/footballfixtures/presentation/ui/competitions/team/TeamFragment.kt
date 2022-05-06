package com.example.footballfixtures.presentation.ui.competitions.team

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.footballfixtures.R
import com.example.footballfixtures.data.mappers.TeamDomainMapper
import com.example.footballfixtures.data.remote.dto.Team
import com.example.footballfixtures.databinding.FragmentTeamBinding
import com.example.footballfixtures.presentation.ui.competitions.competitiondetail.CompetitionsDetailActivity
import com.example.footballfixtures.presentation.ui.competitions.team.squad.SquadActivity
import com.example.footballfixtures.utils.Resource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TeamFragment : Fragment() {
    private var _binding: FragmentTeamBinding? = null
    private val binding get() = _binding!!
    private val teamViewModel: TeamViewModel by viewModels()
    lateinit var teamAdapter: TeamAdapter
    private var competitionId: Int? = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTeamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        teamAdapter = TeamAdapter{team ->
            val intent = Intent(requireContext(), SquadActivity::class.java)
            intent.putExtra("teamId", team.id)
            intent.putExtra("teamName", team.name)
            intent.putExtra("teamLogo", team.crestUrl)
            startActivity(intent)
        }

        val intent = activity?.intent
        competitionId = intent?.getIntExtra("competitionId", 0)
        teamViewModel.getCompetitions(competitionId)
        teamViewModel.getTeamListFromDatabase(competitionId)

        observeSavedTeams()
        observeTeams()

        // implement swipe to refresh
        binding.competitionFragmentSwipeRefreshLayout.setOnRefreshListener {
            teamViewModel.getCompetitions(competitionId)
            binding.competitionFragmentSwipeRefreshLayout.isRefreshing = false
        }
    }

    // save to room database
    private fun observeTeams() {
        teamViewModel.team.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    val teamsList: List<Team>? = it.value.teams

                   teamViewModel.saveTeam((TeamDomainMapper(teamsList,competitionId)).teamDomain)
                }

                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    Snackbar.make(binding.teamsRv, it.error, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }

                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE               }
            }
        }
    }

    // read from room database
    private fun observeSavedTeams() {
        teamViewModel.savedTeam.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    val teamsList: List<Team> = it.value
                    val teamsRv = binding.teamsRv
                    teamsRv.adapter = teamAdapter
                    teamAdapter.submitList(teamsList)
                }

                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    Snackbar.make(binding.teamsRv, it.error, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }

                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE               }
            }
        }
    }
}