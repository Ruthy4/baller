package com.example.footballfixtures.presentation.ui.competitions.competitiondetail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.footballfixtures.databinding.ActivityCompetitionsDetailBinding
import com.example.footballfixtures.presentation.ui.competitions.fixtures.FixturesViewModel
import com.example.footballfixtures.presentation.ui.competitions.table.TableViewModel
import com.example.footballfixtures.presentation.ui.competitions.team.TeamViewModel
import com.example.footballfixtures.utils.viewPagerItem
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompetitionsDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityCompetitionsDetailBinding
    private val tableViewModel: TableViewModel by viewModels()
    private val teamViewModel: TeamViewModel by viewModels()
    private val fixturesViewModel: FixturesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompetitionsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val competitionIntent = intent
        val competitionName = competitionIntent.getStringExtra("competitionName")
        val competitionId = competitionIntent.getIntExtra("competitionId", 0)
        binding.tvTitle.text = competitionName

        // get competition details
        tableViewModel.getCompetitionTables(competitionId)
        teamViewModel.getCompetitions(competitionId)
        fixturesViewModel.getFixtures(competitionId)

        val viewPager = binding.viewpager
        val tabLayout = binding.tabLayout

        // attach viewPager to adapter
        val adapter = CompetitionDetailsViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = viewPagerItem[position]
        }.attach()


    }

}