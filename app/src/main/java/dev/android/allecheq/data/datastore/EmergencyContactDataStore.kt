package dev.android.allecheq.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dev.android.allecheq.data.EmergencyContact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

//const val EMERGENCY_CONTACTS_DATASTORE = "emergency_contacts"
//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(EMERGENCY_CONTACTS_DATASTORE)

//class EmergencyContactDataStore(private val dataStore: DataStore<Preferences>) {
//
//    companion object {
//        val CONTACT_NAME_KEY = stringPreferencesKey("contact_name")
//        val CONTACT_RELATIONSHIP_KEY = stringPreferencesKey("contact_relationship")
//        val CONTACT_PHONE_KEY = stringPreferencesKey("contact_phone")
//    }
//
//    suspend fun saveContact(contact: EmergencyContact) {
//        dataStore.edit { preferences ->
//            preferences[CONTACT_NAME_KEY] = contact.name
//            preferences[CONTACT_RELATIONSHIP_KEY] = contact.relationship
//            preferences[CONTACT_PHONE_KEY] = contact.phoneNumber
//        }
//    }
//
//    val contactFlow: Flow<EmergencyContact?> = dataStore.data.map { preferences ->
//        val name = preferences[CONTACT_NAME_KEY]
//        val relationship = preferences[CONTACT_RELATIONSHIP_KEY]
//        val phoneNumber = preferences[CONTACT_PHONE_KEY]
//        if (name != null && relationship != null && phoneNumber != null) {
//            EmergencyContact(name, relationship, phoneNumber)
//        } else {
//            null
//        }
//    }
//}
//
//class EmergencyContactDataStore(private val dataStore: DataStore<Preferences>) {
//
//    companion object {
//        val CONTACT_LIST_KEY = stringPreferencesKey("contact_list")
//    }
//
//    suspend fun saveContact(contact: EmergencyContact) {
//        dataStore.edit { preferences ->
//            val currentList = preferences[CONTACT_LIST_KEY]?.let {
//                Json.decodeFromString<List<EmergencyContact>>(it)
//            } ?: emptyList()
//
//            val updatedList = currentList + contact
//            preferences[CONTACT_LIST_KEY] = Json.encodeToString(updatedList)
//        }
//    }
//
//    suspend fun deleteContact(contact: EmergencyContact) {
//        dataStore.edit { preferences ->
//            val currentList = preferences[CONTACT_LIST_KEY]?.let {
//                Json.decodeFromString<List<EmergencyContact>>(it)
//            } ?: emptyList()
//
//            val updatedList = currentList.filterNot { it == contact }
//            preferences[CONTACT_LIST_KEY] = Json.encodeToString(updatedList)
//        }
//    }
//
//    val contactListFlow: Flow<List<EmergencyContact>> = dataStore.data.map { preferences ->
//        preferences[CONTACT_LIST_KEY]?.let {
//            Json.decodeFromString(it)
//        } ?: emptyList()
//    }
//}



const val EMERGENCY_CONTACTS_DATASTORE = "emergency_contacts"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(EMERGENCY_CONTACTS_DATASTORE)

class EmergencyContactDataStore(private val dataStore: DataStore<Preferences>) {

    companion object {
        val CONTACT_LIST_KEY = stringPreferencesKey("contact_list")
    }

    suspend fun saveContact(contact: EmergencyContact) {
        dataStore.edit { preferences ->
            val currentList = preferences[CONTACT_LIST_KEY]?.let {
                Json.decodeFromString<List<EmergencyContact>>(it)
            } ?: emptyList()

            val updatedList = currentList + contact
            preferences[CONTACT_LIST_KEY] = Json.encodeToString(updatedList)
        }
    }

    suspend fun deleteContact(contact: EmergencyContact) {
        dataStore.edit { preferences ->
            val currentList = preferences[CONTACT_LIST_KEY]?.let {
                Json.decodeFromString<List<EmergencyContact>>(it)
            } ?: emptyList()

            val updatedList = currentList.filterNot { it == contact }
            preferences[CONTACT_LIST_KEY] = Json.encodeToString(updatedList)
        }
    }

    val contactListFlow: Flow<List<EmergencyContact>> = dataStore.data.map { preferences ->
        preferences[CONTACT_LIST_KEY]?.let {
            Json.decodeFromString(it)
        } ?: emptyList()
    }
}


