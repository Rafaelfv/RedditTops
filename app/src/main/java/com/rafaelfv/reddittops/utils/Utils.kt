package com.rafaelfv.reddittops.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


fun FragmentManager.setFragment(
    fragment: Fragment,
    id: Int,
    tag: String
) {
    this.beginTransaction()
        .replace(id, fragment, tag)
        .commit()
}

fun FragmentManager.addFragment(
    fragment: Fragment,
    idContent: Int,
    tag: String
) {
    this.beginTransaction()
        .add(idContent, fragment, tag)
        .addToBackStack(tag)
        .commit()
}

fun removeFragment(fragmentManager: FragmentManager, fragment: Fragment) {
    fragmentManager.beginTransaction()
        .remove(fragment)
        .commitAllowingStateLoss()
}
