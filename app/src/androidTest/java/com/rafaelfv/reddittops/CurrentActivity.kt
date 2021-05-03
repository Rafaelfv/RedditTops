package com.rafaelfv.reddittops

import android.app.Activity
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage

class CurrentActivity {
    companion object {
        var currentActivity: Activity? = null
        fun getActivityInstance(): Activity? {
            getInstrumentation().runOnMainSync {
                val resumedActivities =
                    ActivityLifecycleMonitorRegistry.getInstance()
                        .getActivitiesInStage(Stage.RESUMED)
                for (activity in resumedActivities) {
                    currentActivity = activity
                    break
                }
            }
            return currentActivity
        }
    }
}