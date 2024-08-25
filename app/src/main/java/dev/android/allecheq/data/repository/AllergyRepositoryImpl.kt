package dev.android.allecheq.data.repository

import dev.android.allecheq.data.Allergy
import dev.android.allecheq.data.datastore.AllergyDataStore
import dev.android.allecheq.model.repository.AllergyRepository

class AllergyRepositoryImpl(
    private val allergyDataStore: AllergyDataStore
) : AllergyRepository {
    override suspend fun getSelectedAllergies(): List<Allergy> {
        return allergyDataStore.getSelectedAllergies()
    }

    override suspend fun getUnselectedAllergies(): List<Allergy> {
        return allergyDataStore.getUnselectedAllergies()
    }

    override suspend fun saveAllergies(selectedAllergiesIds: List<Int>) {
        allergyDataStore.saveSelectedAllergy(selectedAllergiesIds)
        TODO("come home")
    }
}