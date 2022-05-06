package com.example.footballfixtures.presentation.ui.competitions.fixtures

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.footballfixtures.data.remote.dto.Match
import com.example.footballfixtures.databinding.ActivityTodaysFixturesBinding
import com.example.footballfixtures.utils.Resource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodaysFixturesActivity : AppCompatActivity() {
    lateinit var binding: ActivityTodaysFixturesBinding
    private val todaysFixturesViewModel: TodaysFixturesViewModel by viewModels()
    lateinit var fixturesAdapter: FixturesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodaysFixturesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fixturesAdapter = FixturesAdapter()

        todaysFixturesViewModel.getFixtures()
        todaysFixturesViewModel.getTodayFixturesListFromDatabase()
        observeTodayFixtures()
        observeTodayFixturesFromDatabase()
    }

    // save to room database
    private fun observeTodayFixtures() {
        todaysFixturesViewModel.todaysFixtures.observe(this) {
            when (it) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    val matchList: List<Match> = it.value.matches
                    todaysFixturesViewModel.saveTodayFixtures(matchList)
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
    private fun observeTodayFixturesFromDatabase() {
        todaysFixturesViewModel.savedTodayFixtures.observe(this) {
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