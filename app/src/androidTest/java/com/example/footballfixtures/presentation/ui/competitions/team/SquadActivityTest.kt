package com.example.footballfixtures.presentation.ui.competitions.team

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.footballfixtures.R
import com.example.footballfixtures.presentation.ui.competitions.team.squad.SquadActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class SquadActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(SquadActivity::class.java)

    @Test
    fun test_that_recycler_view_is_visible() {
        Espresso.onView(ViewMatchers.withId(R.id.squad_rv))
            .check(
                ViewAssertions.matches(
                    Matchers.allOf(
                        ViewMatchers.isDisplayed(),
                    )
                )
            )
    }
}
