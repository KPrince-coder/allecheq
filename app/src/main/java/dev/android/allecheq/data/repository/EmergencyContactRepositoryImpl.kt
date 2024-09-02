package dev.android.allecheq.data.repository

//class EmergencyContactRepositoryImpl(
//    private val emergencyContactDataStore: EmergencyContactDataStore
//) : EmergencyContactRepository {
//    override suspend fun deleteContact(contact: EmergencyContact) {
//        emergencyContactDataStore.deleteContact(contact)
//    }
//
//    override suspend fun saveContact(contact: EmergencyContact) {
//        emergencyContactDataStore.saveContact(contact)
//    }
//
//
//    val contactListFlow = emergencyContactDataStore.contactListFlow
//}

//class EmergencyContactRepositoryImpl(
//    private val dataStore: EmergencyContactDataStore
//) : EmergencyContactRepository {
//
//    override suspend fun saveContact(contact: EmergencyContact) =
//            dataStore.saveContact(contact)
//
//
//    override suspend fun deleteContact(contact: EmergencyContact) =
//            dataStore.deleteContact(contact)
//        }
//
//    val contactListFlow = dataStore.contactListFlow
//}
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