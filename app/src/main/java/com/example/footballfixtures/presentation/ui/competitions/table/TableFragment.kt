package com.example.footballfixtures.presentation.ui.competitions.table

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.footballfixtures.data.remote.dto.Table
import com.example.footballfixtures.data.remote.dto.Team
import com.example.footballfixtures.databinding.FragmentTableBinding
import com.example.footballfixtures.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableFragment : Fragment() {

    private var _binding: FragmentTableBinding? = null
    private val binding get() = _binding!!
    private val tableViewModel: TableViewModel by viewModels()
    private lateinit var tablesRVAdapter: TablesRVAdapter
    private var competitionId: Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val intent = activity?.intent
        competitionId = intent?.getIntExtra("competitionId", 0)

        Log.d("MAIN_COMPETITION", "onViewCreated: $competitionId")

        tableViewModel.getCompetitionTables(competitionId)
        tableViewModel.getTableListFromDatabase(competitionId)


        tablesRVAdapter = TablesRVAdapter()
        observeTableFromDatabase()
        observeTableResponse()

        // implement swipe to refresh
        binding.competitionFragmentSwipeRefreshLayout.setOnRefreshListener {
            tableViewModel.getCompetitionTables(competitionId)
            binding.competitionFragmentSwipeRefreshLayout.isRefreshing = false
        }
    }


    private fun observeTableResponse() {
        tableViewModel.competitionsTable.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    val tableResponse = it.value.standings?.get(0)?.table
                    val tableDomain = tableResponse?.map { tableResponse ->
                        val teamDomain = Team(
                            competitionId = competitionId,
                            id = tableResponse.team.id,
                            name = tableResponse.team.name,
                            crestUrl = tableResponse.team.crestUrl,
                            shortName = tableResponse.team.shortName,
                            tla = tableResponse.team.tla
                        )
                        Table(
                            competitionId = competitionId,
                            id = tableResponse.id,
                            position = tableResponse.position,
                            team = teamDomain,
                            playedGames = tableResponse.playedGames,
                            won = tableResponse.won,
                            draw = tableResponse.draw,
                            lost = tableResponse.lost,
                            points = tableResponse.points,
                            goalsFor = tableResponse.goalsFor,
                            goalsAgainst = tableResponse.goalsAgainst,
                            goalDifference = tableResponse.goalDifference
                        )
                    }

                    Log.d(
                        "COMPETITION",
                        "observeTableResponse:${tableDomain}"
                    )

                    tableViewModel.saveCompetitionTable(tableDomain)
                    Toast.makeText(
                        requireContext(),
                        "${tableViewModel.savedTable.value}",
                        Toast.LENGTH_SHORT
                    ).show()

                }

                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(requireContext(), "No internet", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun observeTableFromDatabase() {
        tableViewModel.savedTable.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    val tablesList: List<Table> = it.value
                    val tablesRv = binding.tablesRv
                    tablesRv.adapter = tablesRVAdapter
                    tablesRVAdapter.submitList(tablesList)
                }

                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Error reading from database",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }
        }
    }
}