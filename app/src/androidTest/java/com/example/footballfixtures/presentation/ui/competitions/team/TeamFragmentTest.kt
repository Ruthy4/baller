package com.example.footballfixtures.presentation.ui.competitions.team

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.footballfixtures.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class TeamFragmentTest {

    private lateinit var scenario: FragmentScenario<TeamFragment>

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        scenario = launchFragment(
            themeResId = R.style.Theme_FootballFixtures_NoActionBar
        )
    }

    @Test
    fun showSomeResults() {
        scenario.onFragment {
            val recyclerView = it.view?.findViewById<RecyclerView>(R.id.teams_rv)
            MatcherAssert.assertThat(recyclerView?.adapter, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(recyclerView?.adapter?.itemCount, CoreMatchers.`is`(5))
        }
    }
}