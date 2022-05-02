package com.example.footballfixtures.presentation.ui.competitions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.footballfixtures.MainActivity
import com.example.footballfixtures.R
import com.example.footballfixtures.databinding.FragmentCompetitionDetailsBinding
import com.example.footballfixtures.databinding.FragmentCompetitionsBinding
import com.example.footballfixtures.utils.viewPagerItem
import com.google.android.material.tabs.TabLayoutMediator

class CompetitionDetailsFragment : Fragment() {
    private var _binding: FragmentCompetitionDetailsBinding? = null
    val binding get() = _binding!!
    val args: CompetitionDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         _binding = FragmentCompetitionDetailsBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = binding.viewpager
        val tabLayout = binding.tabLayout

        val adapter = CompetitionDetailsViewPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = viewPagerItem[position]
        }.attach()
    }

}