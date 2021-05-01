package com.rafaelfv.reddittops.utils

import com.rafaelfv.reddittops.repository.model.Children

interface ListTopCallback {
    fun onItemSelected(children: Children)
}