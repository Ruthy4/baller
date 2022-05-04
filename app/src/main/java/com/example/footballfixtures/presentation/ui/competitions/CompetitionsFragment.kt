package com.example.footballfixtures.presentation.ui.competitions

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.example.footballfixtures.data.remote.dto.Competition
import com.example.footballfixtures.databinding.FragmentCompetitionsBinding
import com.example.footballfixtures.presentation.ui.competitions.competitiondetail.CompetitionsDetailActivity
import com.example.footballfixtures.presentation.ui.competitions.table.TableViewModel
import com.example.footballfixtures.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompetitionsFragment : Fragment() {
    private var _binding: FragmentCompetitionsBinding? = null
    val binding get() = _binding!!
    private val viewModel: CompetitionsViewModel by viewModels()
    lateinit var competitionsRVAdapter: CompetitionsRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCompetitionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCompetitions()

        competitionsRVAdapter = CompetitionsRVAdapter { competition ->
            val intent = Intent(requireContext(), CompetitionsDetailActivity::class.java)
            intent.putExtra("competitionId", competition.id)
            intent.putExtra("competitionName", competition.name)
            startActivity(intent)
        }


        viewModel.competitions.observe(viewLifecycleOwner) {

            when(it) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    val competitionsList: List<Competition>? = it.value.competitions
                    viewModel.saveCompetitionToDatabase(it.value.competitions)
                    val competitionRv = binding.competitionsRv
                    competitionRv.adapter = competitionsRVAdapter
                    competitionsRVAdapter.submitList(competitionsList)
                }

                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(requireContext(), "An Error occured, Swipe to refresh", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }
        }

        // implement swipe to refresh
        binding.competitionFragmentSwipeRefreshLayout.setOnRefreshListener {
            viewModel.getCompetitions()
            binding.competitionFragmentSwipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}