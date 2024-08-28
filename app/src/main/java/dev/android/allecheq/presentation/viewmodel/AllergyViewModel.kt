package dev.android.allecheq.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.android.allecheq.data.Allergy
import dev.android.allecheq.data.repository.AllergyRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AllergyViewModel(
    private val allergyRepository: AllergyRepositoryImpl
) : ViewModel() {
    private val _allergies = MutableStateFlow<List<Allergy>>(emptyList())
    val allergies = _allergies.asStateFlow()

    init {
        viewModelScope.launch {
            val allergies = allergyRepository.getSelectedAllergies()
            _allergies.value = allergies
            // _selectedAllergyIds.value = allergies.filter { it.isSelected}.map { it.id }
        }
    }

    fun toggleAllergySelection(allergyId: Int, isChecked: Boolean) {
        viewModelScope.launch {
            val currentAllergies = _allergies.value // Get the current allergy list
            val updatedAllergies = currentAllergies.map { allergy ->
                if (allergy.id == allergyId) {
                    allergy.copy(isSelected = isChecked) // Update for this allergy
                } else {
                    allergy
                }
            }
            _allergies.value = updatedAllergies // Update the state with the modified list
            allergyRepository.saveAllergies(updatedAllergies.filter { it.isSelected }.map
            { it.id }) // Save updated selections
        }
    }
}
//     private fun getSelectedAllergyIds(allergies: List<Allergy>): List<Int> {
//         return allergies.filter { it.isSelected }.map { it.id }
//     }
//
//     private fun toggleSelection(selectedAllergyIds: List<Int>, allergyId: Int): List<Int> {
//         return if (selectedAllergyIds.contains(allergyId)) {
//             selectedAllergyIds.filterNot { it == allergyId }
//         } else {
//             selectedAllergyIds + allergyId
//         }
//     }
//
//     private fun updateAllergies(allergies: List<Allergy>, allergyId: Int, newSelection: List<Int>): List<Allergy> {
//         return allergies.map { allergy ->
//             if (allergy.id == allergyId) {
//                 allergy.copy(isSelected = newSelection.contains(allergyId))
//             } else {
//                 allergy
//             }
//         }
//     }
// }
// class AllergyViewModel(
//     private val allergyRepository: AllergyRepositoryImpl
// ) : ViewModel() {
//
//     private val _allergyState = MutableStateFlow<List<Allergy>>(emptyList())
//     val allergies = _allergyState.asStateFlow()
//
//     private val _selectedAllergyIds = MutableStateFlow<List<Int>>(emptyList())
//     val selectedAllergyIds = _selectedAllergyIds.asStateFlow()
//
//     init {
//         viewModelScope.launch {
//             val allergies = allergyRepository.getSelectedAllergies()
//                 _allergyState.value = allergies
//                 _selectedAllergyIds.value = allergies.filter { it.isSelected}.map { it.id }
//             }
//         }
//     }
//
//     fun toggleAllergySelection(allergyId: Int) {
//         viewModelScope.launch {
//             _allergyState.value = _allergyState.value.map { allergy ->
//                 if (allergy.id == allergyId) {
//                     allergy.copy(isSelected = !allergy.isSelected)
//                 } else {
//                     allergy
//                 }
//             }.also { updatedAllergies ->
//                 _selectedAllergyIds.value = updatedAllergies.filter { it.isSelected }.map { it.id }
//                 allergyRepository.saveAllergies(_selectedAllergyIds.value)
//             }
//         }
//     }
// }