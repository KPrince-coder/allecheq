package dev.android.allecheq.presentation.viewmodel.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.android.allecheq.data.repository.EmergencyContactRepositoryImpl
import dev.android.allecheq.presentation.viewmodel.EmergencyContactViewModel

class EmergencyContactViewModelFactory(private val repository: EmergencyContactRepositoryImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmergencyContactViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EmergencyContactViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}