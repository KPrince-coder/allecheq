package dev.android.allecheq.data.repository


import dev.android.allecheq.data.EmergencyContact
import dev.android.allecheq.data.datastore.EmergencyContactDataStore
import kotlinx.coroutines.flow.Flow

class EmergencyContactRepositoryImpl(private val dataStore: EmergencyContactDataStore) {

    val contactListFlow: Flow<List<EmergencyContact>> = dataStore.contactListFlow

    suspend fun saveContact(contact: EmergencyContact) {
        dataStore.saveContact(contact)
    }

    suspend fun deleteContact(contact: EmergencyContact) {
        dataStore.deleteContact(contact)
    }
}