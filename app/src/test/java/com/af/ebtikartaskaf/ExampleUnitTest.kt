package com.af.ebtikartaskaf

import com.af.ebtikartaskaf.ui.fragments.home.HomeViewModel
import org.mockito.Mockito
import org.hamcrest.MatcherAssert
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {

    private var homeViewModel: HomeViewModel? = null

    @Before
    fun setup() {
        homeViewModel = Mockito.mock(HomeViewModel::class.java)
    }

    @Test
    fun verifyHomeViewModel() {
        MatcherAssert.assertThat(homeViewModel, CoreMatchers.`is`(CoreMatchers.instanceOf(HomeViewModel::class.java)))
        Mockito.verifyNoMoreInteractions(homeViewModel)
    }

    companion object{
        private const val TAG = "ExampleUnitTest"
    }

}