package dev.android.allecheq.presentation.viewmodel.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.android.allecheq.data.repository.AllergyRepositoryImpl
import dev.android.allecheq.presentation.viewmodel.AllergyViewModel


class AllergyViewModelFactory(private val repository: AllergyRepositoryImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllergyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AllergyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
