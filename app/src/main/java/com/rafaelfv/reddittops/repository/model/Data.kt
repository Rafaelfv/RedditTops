package com.rafaelfv.reddittops.repository.model

data class Data(
    val after: String,
    val children: List<Children>,
    val dist: Int,
    val modhash: String
)