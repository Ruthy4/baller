package com.example.footballfixtures.presentation.ui.competitions.table

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat.getExtras
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.footballfixtures.data.remote.dto.Table
import com.example.footballfixtures.databinding.FragmentTableBinding
import com.example.footballfixtures.presentation.ui.competitions.CompetitionDetailsFragmentArgs
import com.example.footballfixtures.presentation.ui.competitions.CompetitionsViewModel
import com.example.footballfixtures.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableFragment : Fragment() {

    private var _binding: FragmentTableBinding? = null
    private val binding get() = _binding!!
    private val tableViewModel: TableViewModel by viewModels()
    private lateinit var tablesRVAdapter: TablesRVAdapter

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
        val competitionId = intent?.getIntExtra("competitionId", 0)

        tableViewModel.getTableListFromDatabase()

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
                    tableViewModel.saveCompetitionTable(it.value.standings?.get(0)?.table)
                }

                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(requireContext(), "An Error occurred, Swipe to refresh", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE                }
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
                    Toast.makeText(requireContext(), "An Error occurred, Swipe to refresh", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }
        }
    }
}