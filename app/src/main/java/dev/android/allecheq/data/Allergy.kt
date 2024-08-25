package dev.android.allecheq.data

import androidx.annotation.StringRes

data class Allergy(
    val id: Int,
    @StringRes val name: Int,
    // val image: Int,
    var isSelected: Boolean = false

)
