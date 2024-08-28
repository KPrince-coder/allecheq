package dev.android.allecheq.data.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dev.android.allecheq.data.Allergy
import dev.android.allecheq.data.AllergyData
import dev.android.allecheq.presentation.utils.encodedStringToList
import dev.android.allecheq.presentation.utils.listToEncodedString
import kotlinx.coroutines.flow.firstOrNull

private const val TAG = "AllergyDataStore"
const val USER_ALLERGY_DATASTORE = "user_allergy_datastore"
val Context.preferenceDataStore: DataStore<Preferences>
        by preferencesDataStore(USER_ALLERGY_DATASTORE)

class AllergyDataStore(context: Context) {
    /**
     *  @param
     *  Accesses the DataStore instance associated with the given context.
     */
    private val pref = context.preferenceDataStore

    companion object {
        val SELECTED_ALLERGIES_KEYS = stringPreferencesKey("selected_allergies")
    }

    suspend fun getSelectedAllergies(): List<Allergy> {
        /**
         * Retrieves allergies from the dataStore
         * @return List of allergies
         */
        val preferences = pref.data.firstOrNull() ?: emptyPreferences()
        try {
            val encodedAllergies = preferences[SELECTED_ALLERGIES_KEYS]
            val decodedAllergies = encodedAllergies?.let { encodedStringToList(it) } ?: emptyList()

            return AllergyData.allergies.map { allergy ->
                allergy.copy(
                    isSelected = decodedAllergies.any { it == allergy.id }
                )
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to retrieve allergies", e)
            return getUnselectedAllergies()
        }
    }

    fun getUnselectedAllergies(): List<Allergy> {
        /**
         * @return Unselected allergies which are in the `AllergyData`
         */
        return AllergyData.allergies.map { it.copy(isSelected = false) }
    }

    suspend fun saveSelectedAllergy(selectedAllergiesIds: List<Int>) {
        /**
         * Saves selected allergies to the dataStore
         */
        pref.edit { preferences ->
            val encodedList = listToEncodedString(selectedAllergiesIds)
            preferences[SELECTED_ALLERGIES_KEYS] = encodedList
        }
    }
}