package com.rafaelfv.reddittops

import androidx.recyclerview.widget.RecyclerView
import com.rafaelfv.reddittops.CurrentActivity.Companion.getActivityInstance


fun listTop(func: ListTopRobot.() -> Unit) = ListTopRobot().apply { func() }
class ListTopRobot : BaseTestRobot() {
    fun clickDismissAll() = clickButton(R.id.button_dismiss_all)
    fun clickOnItem() = R.id.recyclerview_list_top.clickOnRecyclerAtPosition(2)
    fun scrollToBottom() = R.id.recyclerview_list_top.scrollToBottom()
    fun verifyDetailView() = R.id.title_top_detail.verifyIsDisplayed()
    fun getCountInRecycler(): Int {
        val recyclerView =
            getActivityInstance()?.findViewById<RecyclerView>(R.id.recyclerview_list_top)
        return recyclerView?.adapter?.itemCount ?: 0
    }

}