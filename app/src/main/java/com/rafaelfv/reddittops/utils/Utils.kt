package com.rafaelfv.reddittops.utils

import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.rafaelfv.reddittops.R


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


fun addOne(number: Int): Int {
    var num = number
    num += 1
    return num
}


fun ImageView.setImageFromUrl(url: String) =
    Glide
        .with(context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.empty_image)
        .into(this)
