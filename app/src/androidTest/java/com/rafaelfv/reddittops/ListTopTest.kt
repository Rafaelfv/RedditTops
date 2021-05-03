package com.rafaelfv.reddittops

import android.content.Intent
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.delay

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ListTopTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun verifyItemTopDetail() {
        listTop {
            Thread.sleep(5000)
            clickOnItem()
            Thread.sleep(1000)
            verifyDetailView()
        }
    }

    @Test
    fun verifyItemsCount() {
        listTop {
            Thread.sleep(4000)
            val count = getCountInRecycler()
            assertEquals(10, count)
        }
    }

    @Test
    fun dismissAllAction() {
        listTop {
            Thread.sleep(4000)
            clickDismissAll()
            Thread.sleep(1000)
            val count = getCountInRecycler()
            assertEquals(0, count)
        }
    }

    @Test
    fun paginationTest() {
        listTop {
            Thread.sleep(4000)
            val count = getCountInRecycler()
            assertEquals(10, count)
            scrollToBottom()
            Thread.sleep(1000)
            val countAfter = getCountInRecycler()
            assertEquals(20, countAfter)
            assertTrue(count < countAfter)
        }
    }

}