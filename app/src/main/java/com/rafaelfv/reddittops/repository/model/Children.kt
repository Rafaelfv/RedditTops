package com.rafaelfv.reddittops.repository.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Children(
    val data: DataChildren,
    val kind: String
): Parcelable