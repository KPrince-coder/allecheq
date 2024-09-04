

package dev.android.allecheq.data.repository

import dev.android.allecheq.data.Allergy
import dev.android.allecheq.data.datastore.AllergyDataStore

class AllergyRepositoryImpl(private val dataStore: AllergyDataStore) {

    suspend fun getSelectedAllergies(): List<Allergy> {
        return dataStore.getSelectedAllergies()
    }

    suspend fun saveSelectedAllergy(selectedAllergiesIds: List<Int>) {
        dataStore.saveSelectedAllergy(selectedAllergiesIds)
    }

//    suspend fun toggleAllergySelection(allergyId: Int) {
//        dataStore.toggleAllergySelection(allergyId)
//    }
}
