package dev.android.allecheq.model.repository

import dev.android.allecheq.data.EmergencyContact
import kotlinx.coroutines.flow.Flow

interface EmergencyContactRepository {
    suspend fun saveContact(contact: EmergencyContact): Unit
    suspend fun deleteContact(contact: EmergencyContact): Unit
//    val contactListFlow: Flow<List<EmergencyContact>>
}