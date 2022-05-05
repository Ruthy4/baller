package com.example.footballfixtures.presentation.ui.competitions

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.footballfixtures.R
import com.example.footballfixtures.presentation.ui.MainActivity
import com.example.footballfixtures.presentation.ui.competitions.fixtures.FixturesFragment
import com.example.footballfixtures.presentation.ui.competitions.team.TeamAdapter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CompetitionFragmentTest {

    private lateinit var scenario: FragmentScenario<FixturesFragment>

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    @Before
    fun init() {
        scenario = launchFragment(
            themeResId = R.style.Theme_FootballFixtures_NoActionBar
        )
        scenario.moveToState(Lifecycle.State.STARTED)
    }


    @Test
    fun test_isCompetitionsFragmentVisible_onAppLaunch() {
        onView(withId(R.id.competitions_rv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}