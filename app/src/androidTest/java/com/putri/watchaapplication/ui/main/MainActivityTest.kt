package com.putri.watchaapplication.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.putri.watchaapplication.R
import com.putri.watchaapplication.utils.DataMedia.setDummyMovie
import com.putri.watchaapplication.utils.DataMedia.setDummyShow
import com.putri.watchaapplication.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {
    private val dummyMovie = setDummyMovie()

    private val dummyShow = setDummyShow()

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.img_media)).check(matches(isDisplayed()))
        onView(withId(R.id.collapse_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.collapse_toolbar)).check(matches(withContentDescription(dummyMovie[0].movieTitle)))
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_share_media)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_share_media)).perform(click())
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyMovie[0].movieDesc)))
        onView(withId(R.id.tv_date)).check(matches(withText(dummyMovie[0].movieRelease)))
    }

    @Test
    fun loadTvShow() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyShow.size))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.img_media)).check(matches(isDisplayed()))
        onView(withId(R.id.collapse_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.collapse_toolbar)).check(matches(withContentDescription(dummyShow[0].showTitle)))
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_share_media)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_share_media)).perform(click())
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyShow[0].showDesc)))
        onView(withId(R.id.tv_date)).check(matches(withText(dummyShow[0].showRelease)))
    }

    @Test
    fun loadFavMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.fav_media)).perform(click())
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.img_media)).check(matches(isDisplayed()))
        onView(withId(R.id.collapse_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.collapse_toolbar)).check(matches(withContentDescription(dummyMovie[0].movieTitle)))
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())
        onView(withId(R.id.btn_share_media)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyMovie[0].movieDesc)))
        onView(withId(R.id.tv_date)).check(matches(withText(dummyMovie[0].movieRelease)))
    }

    @Test
    fun loadFavShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.fav_media)).perform(click())
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.img_media)).check(matches(isDisplayed()))
        onView(withId(R.id.collapse_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.collapse_toolbar)).check(matches(withContentDescription(dummyShow[0].showTitle)))
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())
        onView(withId(R.id.btn_share_media)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyShow[0].showDesc)))
        onView(withId(R.id.tv_date)).check(matches(withText(dummyShow[0].showRelease)))
    }

}