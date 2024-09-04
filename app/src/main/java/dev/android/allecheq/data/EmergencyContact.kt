package dev.android.allecheq.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class EmergencyContact(
    val name: String,
    val relationship: String,
    @SerialName("phone_number") val phoneNumber: String
)

