package com.example.footballfixtures.presentation.ui.competitions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.footballfixtures.presentation.ui.competitions.fixtures.FixturesFragment
import com.example.footballfixtures.presentation.ui.competitions.table.TableFragment
import com.example.footballfixtures.presentation.ui.competitions.team.TeamFragment
import com.example.footballfixtures.utils.NUM_TABS


class CompetitionDetailsViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return TableFragment()
            1 -> return FixturesFragment()
            2 -> return TeamFragment()
        }
        return TeamFragment()
    }
}