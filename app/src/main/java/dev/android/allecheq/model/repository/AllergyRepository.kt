package dev.android.allecheq.model.repository

import dev.android.allecheq.data.Allergy

interface AllergyRepository{
    suspend fun getSelectedAllergies(): List<Allergy>
    suspend fun getUnselectedAllergies(): List<Allergy>
    suspend fun saveAllergies(selectedAllergiesIds:List<Int>)
}