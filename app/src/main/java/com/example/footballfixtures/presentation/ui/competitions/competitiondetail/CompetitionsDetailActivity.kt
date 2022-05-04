package com.example.footballfixtures.presentation.ui.competitions.competitiondetail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.footballfixtures.databinding.ActivityCompetitionsDetailBinding
import com.example.footballfixtures.presentation.ui.competitions.CompetitionsRVAdapter
import com.example.footballfixtures.presentation.ui.competitions.CompetitionsViewModel
import com.example.footballfixtures.utils.viewPagerItem
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompetitionsDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityCompetitionsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompetitionsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = binding.viewpager
        val tabLayout = binding.tabLayout

        val adapter = CompetitionDetailsViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = viewPagerItem[position]
        }.attach()

       val competitionIntent = intent
        val competitionName = competitionIntent.getStringExtra("competitionName")
        binding.tvTitle.text = competitionName
    }

}