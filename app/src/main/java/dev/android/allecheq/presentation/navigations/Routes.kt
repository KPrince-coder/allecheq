package dev.android.allecheq.presentation.navigations

import kotlinx.serialization.Serializable

/**
 * @description
 * Different routes for the application
 */
@Serializable
sealed class Routes {
    @Serializable
    data object OnboardingScreen1:Routes()
    @Serializable
    data object OnboardingScreen2:Routes()
    @Serializable
    data object HomeScreen:Routes()
}