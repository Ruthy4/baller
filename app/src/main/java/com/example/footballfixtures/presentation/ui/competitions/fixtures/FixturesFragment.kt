package com.example.footballfixtures.presentation.ui.competitions.fixtures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.footballfixtures.data.mappers.MatchesDomainMapper
import com.example.footballfixtures.data.remote.dto.Match
import com.example.footballfixtures.databinding.FragmentFixturesBinding
import com.example.footballfixtures.utils.Resource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FixturesFragment : Fragment() {

    private var _binding: FragmentFixturesBinding? = null
    private val binding get() = _binding!!
    private val fixturesViewModel: FixturesViewModel by viewModels()
    lateinit var fixturesAdapter: FixturesAdapter
    private var competitionId : Int? = 0
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
         competitionId = intent?.getIntExtra("competitionId", 0)
        fixturesViewModel.getFixtures(competitionId)
        fixturesViewModel.getFixturesListFromDatabase(competitionId)

        fixturesAdapter = FixturesAdapter()
        observeFixtures()
        observeFixturesFromDatabase()

        // implement swipe to refresh
        binding.competitionFragmentSwipeRefreshLayout.setOnRefreshListener {
            fixturesViewModel.getFixtures(competitionId)
            binding.competitionFragmentSwipeRefreshLayout.isRefreshing = false
        }
    }

    // observe live data of apicall and save to database
    private fun observeFixtures() {
        fixturesViewModel.fixtures.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    val matchList: List<Match> = it.value.matches
                    fixturesViewModel.saveFixtures((MatchesDomainMapper(matchList,competitionId)).matchDomain)
                }

                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    Snackbar.make(binding.fixturesRv, it.error, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }

                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE               }
            }
        }
    }

    // read from database
    private fun observeFixturesFromDatabase() {
        fixturesViewModel.savedFixtures.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    val matchList: List<Match> = it.value
                    val fixturesRv = binding.fixturesRv
                    fixturesRv.adapter = fixturesAdapter
                    fixturesAdapter.submitList(matchList)
                }

                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    Snackbar.make(binding.fixturesRv, it.error, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }

                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE               }
            }
        }
    }

}