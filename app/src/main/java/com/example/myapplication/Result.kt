package com.example.myapplication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
    val coreNumber: Int,
    var totalSize: Float,
    var usedSize: Float,
    var items: ArrayList<ItemResult> = arrayListOf(),
) : Parcelable

@Parcelize
data class ItemResult(
    val totalSize: Float, val size: Float, val color: Int
) : Parcelable