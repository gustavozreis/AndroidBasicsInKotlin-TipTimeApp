package com.example.tiptime

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTests {

    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    // test for the default tip options
    @Test
    fun calculate_default_tip() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("50.00"))

        onView(withId(R.id.scroll_view))
            .perform(swipeUp())

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("10,00"))))
    }

    // test if the result is valid when choosing 18 percent tip without round up option
    @Test
    fun calculate_18_percent_tip_rounded_up() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("40.00"))

        onView(withId(R.id.scroll_view))
            .perform(swipeUp())

        onView(withId(R.id.option_eighteen_percent))
            .perform(click())

        onView(withId(R.id.round_up_switch))
            .perform(click())

        onView(withId(R.id.calculate_button))
            .perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("7,20"))))
    }
}