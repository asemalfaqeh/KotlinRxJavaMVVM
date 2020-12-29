package com.af.ebtikartaskaf


import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.af.ebtikartaskaf.ui.MainActivity
import org.hamcrest.Matchers
import org.junit.*
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityHomeFragmentTestUI {

    @get:Rule var activityTestRule = ActivityTestRule(MainActivity::class.java)
    private var mainActivity: MainActivity? = null

    @Before
    fun setUp() {
        mainActivity = activityTestRule.activity
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.af.ebtikartaskaf", appContext.packageName)
    }

    @Test
    fun MainActivityHomeFragmentTestUI() {
        val navController = Navigation.findNavController(mainActivity!!, R.id.nav_host_fragment)
        Assert.assertNotNull(navController)

        // App Bar Test UI ////
        val textView = Espresso.onView(
                Matchers.allOf(ViewMatchers.withText("EbtikarTaskAF"),
                        ViewMatchers.withParent(Matchers.allOf(ViewMatchers.withId(R.id.action_bar),
                                ViewMatchers.withParent(ViewMatchers.withId(R.id.action_bar_container)))),
                        ViewMatchers.isDisplayed()))
        textView.check(ViewAssertions.matches(ViewMatchers.withText("EbtikarTaskAF")))

        val tvName = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.tv_name), ViewMatchers.withText("Gal Gadot"),
                        ViewMatchers.withParent(ViewMatchers.withParent(ViewMatchers.withId(R.id.cv_info))),
                        ViewMatchers.isDisplayed()))
        tvName.check(ViewAssertions.matches(ViewMatchers.withText("Gal Gadot")))

        val imageView = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.imv_p_p),
                        ViewMatchers.withParent(ViewMatchers.withParent(ViewMatchers.withId(R.id.cv_info))),
                        ViewMatchers.isDisplayed()))
        imageView.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        val recyclerView = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.rv_popular_person),
                        ViewMatchers.withParent(ViewMatchers.withParent(ViewMatchers.withId(R.id.nav_host_fragment))),
                        ViewMatchers.isDisplayed()))
        recyclerView.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }


    @After
    fun shutdown() {
        mainActivity = null
    }
}