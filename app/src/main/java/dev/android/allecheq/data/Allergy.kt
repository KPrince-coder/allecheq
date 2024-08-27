package dev.android.allecheq.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.util.UUID

data class Allergy(
    @StringRes val name: Int,
    @DrawableRes val allergyImage: Int,
    @StringRes val allergyImageDescription: Int,
    val id: Int = UUID.randomUUID().hashCode(),
    var isSelected: Boolean = false
)
