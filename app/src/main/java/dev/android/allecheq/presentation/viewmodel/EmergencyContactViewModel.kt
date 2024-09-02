package dev.android.allecheq.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.android.allecheq.data.EmergencyContact
import dev.android.allecheq.data.repository.EmergencyContactRepositoryImpl
import dev.android.allecheq.model.repository.EmergencyContactRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

//class EmergencyContactViewModel(
//    private val repository: EmergencyContactRepositoryImpl
//) : ViewModel() {
//
//    private val _contactList = MutableStateFlow<List<EmergencyContact>>(emptyList())
//    val contactList: StateFlow<List<EmergencyContact>> = _contactList
//
//    init {
//        viewModelScope.launch {
//            repository.contactListFlow.collect {
//                _contactList.value = it
//            }
//        }
//    }
//
//    fun saveContact(name: String, relationship: String, phoneNumber: String) {
//        val newContact = EmergencyContact(name, relationship, phoneNumber)
//        viewModelScope.launch {
//            repository.saveContact(newContact)
//        }
//    }
//
//    fun deleteContact(contact: EmergencyContact) {
//        viewModelScope.launch {
//            repository.deleteContact(contact)
//        }
//    }
//}


import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EmergencyContactViewModel(private val repository: EmergencyContactRepositoryImpl) : ViewModel() {

    val contactList: StateFlow<List<EmergencyContact>> = repository.contactListFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

//    fun saveContact(contact: EmergencyContact) {
//        viewModelScope.launch {
//            repository.saveContact(contact)
//        }
//    }
    fun saveContact(name: String, relationship: String, phoneNumber: String) {
        val newContact = EmergencyContact(name, relationship, phoneNumber)
        viewModelScope.launch {
            repository.saveContact(newContact)
        }
    }

    fun deleteContact(contact: EmergencyContact) {
        viewModelScope.launch {
            repository.deleteContact(contact)
        }
    }
}


