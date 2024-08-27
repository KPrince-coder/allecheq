package dev.android.allecheq.data

import dev.android.allecheq.R

/**
 * @description Default allergies to appear for user to select
 */
object AllergyData {
    val allergies = listOf(
        Allergy(R.string.milk, R.drawable.milk, R.string.milk_image),
        Allergy(R.string.peanuts, R.drawable.peanut, R.string.peanut_image),
        Allergy(R.string.shellfish, R.drawable.shellfish, R.string.shellfish_image),
        Allergy(R.string.soy, R.drawable.soybean, R.string.soy_image),
        Allergy(R.string.tree_nut, R.drawable.tree_nut, R.string.tree_nut_image),
        Allergy(R.string.wheat, R.drawable.wheat, R.string.wheat_image),
        Allergy(R.string.eggs, R.drawable.egg, R.string.egg_image),
        Allergy(R.string.fish, R.drawable.fish, R.string.fish_image)
    )
}