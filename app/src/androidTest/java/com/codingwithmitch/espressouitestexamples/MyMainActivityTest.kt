package com.codingwithmitch.espressouitestexamples

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.codingwithmitch.espressouitestexamples.MainActivity.Companion.buildToastMessage
import org.hamcrest.Matchers.not
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MyMainActivityTest{
    @Test
    fun test_showDialog_captureNameInput() {
        // Given
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        val expectedName = "Egor"

        // Execute and verify
        onView(withId(R.id.button_launch_dialog))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withText(R.string.text_enter_name)).check(matches(isDisplayed()))

        onView(withText(R.string.text_ok)).perform(click())
        onView(withText(R.string.text_enter_name)).check(matches(isDisplayed()))

        // enter name
        onView(withId(R.id.md_input_message)).perform(typeText(expectedName))
        onView(withText(R.string.text_ok))
            .check(matches(isDisplayed()))
            .perform(click())

        // make sure dialog is gone
        onView(withText(R.string.text_enter_name)).check(doesNotExist())

        // confirm name is set
        onView(withId(R.id.text_name)).check(matches(withText(expectedName)))

	// test is toast is displayed
        onView(withText(buildToastMessage(expectedName)))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))

    }
}