package com.example.footballfixtures.presentation.ui.competitions.fixtures

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.footballfixtures.R
import com.example.footballfixtures.data.remote.dto.Match
import com.example.footballfixtures.data.remote.dto.Team
import com.example.footballfixtures.databinding.FragmentFixturesBinding
import com.example.footballfixtures.databinding.FragmentTableBinding
import com.example.footballfixtures.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FixturesFragment : Fragment() {

    private var _binding: FragmentFixturesBinding? = null
    private val binding get() = _binding!!
    private val fixturesViewModel: FixturesViewModel by viewModels()
    lateinit var fixturesAdapter: FixturesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFixturesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val intent = activity?.intent
        val competitionId = intent?.getIntExtra("competitionId", 0)
        fixturesViewModel.getCompetitionTables(competitionId)

        fixturesAdapter = FixturesAdapter()
        observeFixtures()

        // implement swipe to refresh
        binding.competitionFragmentSwipeRefreshLayout.setOnRefreshListener {
            fixturesViewModel.getCompetitionTables(competitionId)
            binding.competitionFragmentSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun observeFixtures() {
        fixturesViewModel.fixtures.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    val matchList: List<Match> = it.value.matches
                    val fixturesRv = binding.fixturesRv
                    fixturesRv.adapter = fixturesAdapter
                    fixturesAdapter.submitList(matchList)
                }

                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(requireContext(), "An Error occured", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE               }
            }
        }
    }

}