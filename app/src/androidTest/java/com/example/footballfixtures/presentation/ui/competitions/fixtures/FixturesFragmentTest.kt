package com.example.footballfixtures.presentation.ui.competitions.fixtures

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.footballfixtures.R
import com.example.footballfixtures.presentation.ui.MainActivity
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class FixturesFragmentTest: TestCase() {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun test_isFixturesFragmentVisible_onAppLaunch() {
        onView(withId(R.id.fixtures_rv)).check(matches(isDisplayed()))
    }
}



