package com.rafaelfv.reddittops.utils

import com.rafaelfv.reddittops.repository.model.Children

/**
 * Interface to communicate ListTopFragment to MainActivity
 */
interface ListTopCallback {
    fun onItemSelected(children: Children)
}